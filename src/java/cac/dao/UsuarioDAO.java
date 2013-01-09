/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.dao;

import cac.db.DataBase;
import cac.db.Funcao;
import cac.db.Permissao;
import cac.db.Setor;
import cac.db.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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

        PreparedStatement ps = (PreparedStatement) db.getPreparedStatement("INSERT INTO NTE.USUARIOS VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        ps.setString(1, null);
        ps.setString(2, usr.getNome());
        ps.setString(3, usr.getSobrenome());
        ps.setInt(4, usr.getSetor().getIdsetor());
        ps.setInt(5, usr.getFuncao().getIdfuncao());
        ps.setDate(6, usr.getDatanascimento());
        ps.setDate(7, usr.getDatacadastro());
        ps.setInt(8, usr.getCadastrador());
        ps.setString(9, usr.getTelefone());
        ps.setString(10, usr.getMatricula());
        ps.setString(11, usr.getUsuario());
        ps.setString(12, usr.getSenha());
        ps.setObject(13, usr.getPermissao());

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
        ps.setDate(5, usr.getDatanascimento());
        ps.setDate(6, usr.getDatacadastro());
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
        usr.setDatanascimento(rs.getDate("datanascimento"));
        usr.setDatacadastro(rs.getDate("datacadastro"));
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
                usr.setDatanascimento(rs.getDate("datanascimento"));
                usr.setDatacadastro(rs.getDate("datacadastro"));
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
            usr.setDatanascimento(rs.getDate("datanascimento"));
            usr.setDatacadastro(rs.getDate("datacadastro"));
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
}
