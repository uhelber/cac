/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.dao;

import cac.classes.ConverteData;
import cac.classes.Mensagem;
import cac.db.DataBase;
import cac.db.Funcao;
import cac.db.Permissao;
import cac.db.Setor;
import cac.db.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;

/**
 *
 * @author UhelberC
 */
public class UsuarioDAO {

    DataBase db;

    public UsuarioDAO() throws ClassNotFoundException, SQLException {
    }
    private String campoIdUsuario;
    private String campoUsuario;
    private String campoSenha;

    public String getCampoIdUsuario() {
        return campoIdUsuario;
    }

    public String getCampoUsuario() {
        return campoUsuario;
    }

    public String getCampoSenha() {
        return campoSenha;
    }

    public boolean adicionarUsuario(Usuario usr) throws ClassNotFoundException, SQLException {
        this.db = new DataBase();
        ConverteData cDT = new ConverteData();
        
        Date dt = new Date();
        SimpleDateFormat frmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        PreparedStatement ps = (PreparedStatement) db.getPreparedStatement("INSERT INTO NTE.USUARIOS VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        ps.setString(1, null);
        ps.setString(2, usr.getNome());
        ps.setString(3, usr.getSobrenome());
        ps.setInt(4, usr.getSetor().getIdsetor());
        ps.setInt(5, usr.getFuncao().getIdfuncao());
        ps.setString(6, cDT.clu_Data(usr.getDatanascimento()));
        ps.setString(7, frmt.format(dt));
        ps.setInt(8, usr.getCadastrador());
        ps.setString(9, usr.getTelefone());
        ps.setString(10, usr.getMatricula());
        ps.setString(11, usr.getUsuario());
        ps.setString(12, usr.getSenha());
        ps.setInt(13, usr.getPermissao().getIdpermissao());

        boolean retorno = ps.execute();
        ps.close();
        db.getCon().close();

        return retorno;
    }

    public boolean atualizarUsuario(Usuario usr) throws ClassNotFoundException, SQLException {
        this.db = new DataBase();

        PreparedStatement ps = (PreparedStatement) db.getPreparedStatement("UPDATE NTE.USUARIOS SET nome = ?, sobrenome = ?, setor = ?, funcao = ?,"
                + " datanascimento = ?, datacadastro = ?, cadastrador = ?, telefone = ?, matricula = ?, usuario = ?, senha = ?, permissao = ? WHERE idusuarios = ?)");
        ps.setString(1, usr.getNome());
        ps.setString(2, usr.getSobrenome());
        ps.setInt(3, usr.getSetor().getIdsetor());
        ps.setInt(4, usr.getFuncao().getIdfuncao());
        ps.setString(5, usr.getDatanascimento());
        ps.setString(6, usr.getDatacadastro());
        ps.setInt(7, usr.getCadastrador());
        ps.setString(8, usr.getTelefone());
        ps.setString(9, usr.getMatricula());
        ps.setString(10, usr.getUsuario());
        ps.setString(11, usr.getSenha());
        ps.setObject(12, usr.getPermissao());
        ps.setInt(13, usr.getIdusuarios());

        boolean retorno = ps.execute();
        ps.close();
        db.getCon().close();

        return retorno;
    }

    public List<Usuario> getTodosUsuarios() throws ClassNotFoundException, SQLException {
        this.db = new DataBase();

        List<Usuario> usuarios = new LinkedList<Usuario>();
        ResultSet rs = db.getStatement().executeQuery("SELECT * FROM NTE.USUARIOS");
        while (rs.next()) {
            Usuario usr = new Usuario();
            polularListaUsuario(usr, rs);
            usuarios.add(usr);
        }
        rs.close();
        db.getCon().close();

        return usuarios;
    }

    private void polularListaUsuario(Usuario usr, ResultSet rs) throws SQLException, ClassNotFoundException {
        SetorDAO setorDAO = new SetorDAO();
        Setor setor = setorDAO.getPorIdSetor(rs.getInt("setor"));
        
        FuncaoDAO funcaoDAO = new FuncaoDAO();
        Funcao funcao = funcaoDAO.getPorIdFuncao(rs.getInt("funcao"));
        
        usr.setIdusuarios(rs.getInt("idusuarios"));
        usr.setNome(rs.getString("nome"));
        usr.setSobrenome(rs.getString("sobrenome"));
        usr.setSetor(setor);
        usr.setFuncao(funcao);
        usr.setDatanascimento(rs.getString("datanascimento"));
        usr.setDatacadastro(rs.getString("datacadastro"));
        usr.setCadastrador(rs.getInt("cadastrador"));
        usr.setTelefone(rs.getString("telefone"));
        usr.setMatricula(rs.getString("matricula"));
        usr.setUsuario(rs.getString("usuario"));
        usr.setSenha(rs.getString("senha"));
    }

    public Usuario validarUsuario(String usuario, String senha) throws ClassNotFoundException, SQLException {
        this.db = new DataBase();
        Usuario usr = null;

        if ((!usuario.equals("")) && (!senha.equals(""))) {
            PreparedStatement ps = (PreparedStatement) db.getPreparedStatement("SELECT * FROM NTE.USUARIOS WHERE USUARIO = ? AND SENHA = ?");
            ps.setString(1, usuario);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usr = new Usuario();

                PermissaoDAO permissaoDAO = new PermissaoDAO();
                Permissao permissao = permissaoDAO.getPorIdPermissao(rs.getInt("permissao"));
                
                SetorDAO setorDAO = new SetorDAO();
                Setor setor = setorDAO.getPorIdSetor(rs.getInt("setor"));
                
                FuncaoDAO funcaoDAO = new FuncaoDAO();
                Funcao funcao = funcaoDAO.getPorIdFuncao(rs.getInt("funcao"));

                usr.setIdusuarios(rs.getInt("idusuarios"));
                usr.setNome(rs.getString("nome"));
                usr.setSobrenome(rs.getString("sobrenome"));
                usr.setSetor(setor);
                usr.setFuncao(funcao);
                usr.setDatanascimento(rs.getString("datanascimento"));
                usr.setDatacadastro(rs.getString("datacadastro"));
                usr.setCadastrador(rs.getInt("cadastrador"));
                usr.setTelefone(rs.getString("telefone"));
                usr.setMatricula(rs.getString("matricula"));
                usr.setUsuario(rs.getString("usuario"));
                usr.setSenha(rs.getString("senha"));
                usr.setPermissao(permissao);

            }

            ps.close();
            rs.close();
        }

        db.getCon().close();

        return usr;
    }

    public Usuario getPorIdUsuario(int id) throws ClassNotFoundException, SQLException {
        this.db = new DataBase();

        PreparedStatement ps = (PreparedStatement) db.getPreparedStatement("SELECT * FROM NTE.USUARIOS WHERE idusuarios = ?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        Usuario usr = new Usuario();

        if (rs.next()) {
            SetorDAO setorDAO = new SetorDAO();
            Setor setor = setorDAO.getPorIdSetor(rs.getInt("setor"));
            
            FuncaoDAO funcaoDAO = new FuncaoDAO();
            Funcao funcao = funcaoDAO.getPorIdFuncao(rs.getInt("funcao"));
            
            usr.setIdusuarios(rs.getInt("idusuarios"));
            usr.setNome(rs.getString("nome"));
            usr.setSobrenome(rs.getString("sobrenome"));
            usr.setSetor(setor);
            usr.setFuncao(funcao);
            usr.setDatanascimento(rs.getString("datanascimento"));
            usr.setDatacadastro(rs.getString("datacadastro"));
            usr.setCadastrador(rs.getInt("cadastrador"));
            usr.setTelefone(rs.getString("telefone"));
            usr.setMatricula(rs.getString("matricula"));
            usr.setUsuario(rs.getString("usuario"));
            usr.setSenha(rs.getString("senha"));
        }

        ps.close();
        rs.close();
        db.getCon().close();

        return usr;
    }
    
    public Integer verificarUsuarioJaCadastrado(Usuario usr) throws ClassNotFoundException, SQLException{
        this.db = new DataBase();
        Mensagem msn = new Mensagem();
        Integer ir;
        
        PreparedStatement ps = (PreparedStatement) this.db.getPreparedStatement("SELECT * FROM NTE.USUARIOS"
                + " WHERE usuario = ?");
        ps.setString(1, usr.getUsuario());

        ResultSet rs = ps.executeQuery();
        rs.last();
        
        if(rs.getRow() == 0)
        {
            this.adicionarUsuario(usr);
            msn.EviarMensagens("", FacesMessage.SEVERITY_INFO, "Usuário cadastrado com sucesso...", "");
            ir = 0;
        }
        else{
            msn.EviarMensagens("", FacesMessage.SEVERITY_ERROR, "Já existe outro usuário com esse login...", "");
            ir = 1;
        }
                
        rs.close();
        ps.close();
        this.db.getCon().close();
        
        return ir;
    }
}
