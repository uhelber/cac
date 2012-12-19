/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.dao;

import cac.db.DataBase;
import cac.db.Chamado;
import cac.db.Parecer;
import cac.db.Status;
import cac.db.Usuario;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author UhelberC
 */
public class ChamadoDAO{
    DataBase db;
    private UsuarioDAO usrDAO = new UsuarioDAO();
    private StatusDAO stsDAO = new StatusDAO();

    public ChamadoDAO() throws ClassNotFoundException, SQLException {
    }

    public boolean adicionarChamado(Chamado chmd, Usuario usr) throws ClassNotFoundException, SQLException {
        this.db = new DataBase();
        
        PreparedStatement ps = (PreparedStatement) db.getPreparedStatement("INSERT INTO NTE.chamado VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        Date dt = new Date();
        SimpleDateFormat frmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        boolean retorno = false;

        if (chmd.getEscola() != null) {
            ps.setString(1, null);
            ps.setString(2, chmd.getCidade());
            ps.setString(3, chmd.getBairro());
            ps.setString(4, chmd.getEscola());
            ps.setString(5, chmd.getContato());
            ps.setString(6, chmd.getTelefone());
            ps.setInt(7, 4);
            ps.setString(8, chmd.getDescricao());
            ps.setInt(9, usr.getIdusuarios());
            ps.setString(10, frmt.format(dt));
        }
        
        
        retorno = ps.execute();
        ps.close();
        db.getCon().close();

        return retorno;
    }

    public boolean atualizarChamado(Chamado chmd, Parecer parecer, Usuario usr) throws ClassNotFoundException, SQLException {
        this.db = new DataBase();
        ParecerDAO prcrDAO = new ParecerDAO();
        
        PreparedStatement ps;
        ps = (PreparedStatement) db.getPreparedStatement("UPDATE NTE.chamado SET cidade = ?, bairro = ?, escola = ?,"
                + " contato = ?, telefone = ?, status = ?, descricao = ?, dataabertura = ? WHERE idchamado = ?");
        ps.setString(1, chmd.getCidade());
        ps.setString(2, chmd.getBairro());
        ps.setString(3, chmd.getEscola());
        ps.setString(4, chmd.getContato());
        ps.setString(5, chmd.getTelefone());
        ps.setInt(6, chmd.getStatus().getIdstatus());
        ps.setString(7, chmd.getDescricao());
        ps.setString(8, chmd.getDataabertura());
        ps.setInt(9, chmd.getIdchamado());
        
        //prcrDAO.adicionarParecer(chmd, parecer, usr);
        
        boolean retorno = ps.execute();
        ps.close();
        db.getCon().close();

        return retorno;
    }
    
    public List<Chamado> getTodosChamados() throws ClassNotFoundException, SQLException {
        this.db = new DataBase();
        
        LinkedList<Chamado> chamado = new LinkedList<Chamado>();
        ResultSet rs = db.getStatement().executeQuery("SELECT * FROM NTE.chamado WHERE status <> '7' ORDER BY dataabertura");
        while (rs.next()) {
            Chamado chmd = new Chamado();
            polularListaChamado(chmd, rs);
            chamado.add(chmd);
        }
        rs.close();
        db.getCon().close();
        
        return chamado;
    }

    private void polularListaChamado(Chamado chmd, ResultSet rs) throws SQLException, ClassNotFoundException {
        UsuarioDAO usrDAO = new UsuarioDAO();
        Usuario usr = usrDAO.getPorIdUsuario(rs.getInt("abertopor"));
        StatusDAO stsDAO = new StatusDAO();
        Status sts = stsDAO.getPorIdStatus(rs.getInt("status"));

        chmd.setIdchamado(rs.getInt("idchamado"));
        chmd.setCidade(rs.getString("cidade"));
        chmd.setBairro(rs.getString("bairro"));
        chmd.setEscola(rs.getString("escola"));
        chmd.setContato(rs.getString("contato"));
        chmd.setTelefone(rs.getString("telefone"));
        chmd.setStatus(sts);
        chmd.setDescricao(rs.getString("descricao"));
        chmd.setAbertopor(rs.getInt("abertopor"));
        chmd.setDataabertura(rs.getString("dataabertura"));

    }

    public Chamado getPorIdChamado(int id) throws ClassNotFoundException, SQLException {
        this.db = new DataBase();
        
        PreparedStatement ps = (PreparedStatement) db.getPreparedStatement("SELECT * FROM NTE.USUARIOS WHERE 'idchamado' = ?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        Chamado chmd = new Chamado();
        StatusDAO stsDAO = new StatusDAO();
        Status sts = stsDAO.getPorIdStatus(rs.getInt("status"));

        chmd.setIdchamado(rs.getInt("idchamado"));
        chmd.setCidade(rs.getString("cidade"));
        chmd.setBairro(rs.getString("bairro"));
        chmd.setEscola(rs.getString("escola"));
        chmd.setContato(rs.getString("contato"));
        chmd.setTelefone(rs.getString("telefone"));
        chmd.setStatus(sts);
        chmd.setDescricao(rs.getString("descrcao"));
        chmd.setAbertopor(rs.getInt("abertopor"));
        chmd.setDataabertura(rs.getString("dataabertura"));

        ps.close();
        rs.close();
        this.db.getCon().close();

        return chmd;
    }
}
