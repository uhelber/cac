/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.dao;

import cac.db.Chamado;
import cac.db.DataBase;
import cac.db.Parecer;
import cac.db.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author UhelberC
 */
public class ParecerDAO {

    DataBase db;

    public boolean adicionarParecer(Chamado chmd, Parecer parecer, Usuario usr) throws SQLException, ClassNotFoundException {
        this.db = new DataBase();

        PreparedStatement ps = (PreparedStatement) db.getPreparedStatement("INSERT INTO nte.parecer VALUE (?, ?, ?, ?, ?, ?)");
        
        Date dt = new Date();
        SimpleDateFormat frmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        
        boolean retorno = false;

        if ((parecer.getParecer() != null) || (parecer.getParecer().equals(""))) {
            ps.setString(1, null);
            ps.setInt(2, usr.getIdusuarios());
            ps.setString(3, frmt.format(dt));
            ps.setString(4, frmt.format(dt));
            ps.setString(5, parecer.getParecer());
            ps.setInt(6, chmd.getIdchamado());
        }

        
        retorno = ps.execute();
        ps.close();
        db.getCon().close();

        return retorno;
    }

    public List<Parecer> getTodosPareceres() throws ClassNotFoundException, SQLException, ParseException {
        this.db = new DataBase();

        LinkedList<Parecer> parecer = new LinkedList<Parecer>();
        ResultSet rs = db.getStatement().executeQuery("SELECT * FROM NTE.parecer");

        while (rs.next()) {
            Parecer prcr = new Parecer();
            polularListaParecer(prcr, rs);
            parecer.add(prcr);
        }

        rs.close();
        db.getCon().close();

        return parecer;
    }

    private void polularListaParecer(Parecer prcr, ResultSet rs) throws SQLException, ClassNotFoundException, ParseException {
        UsuarioDAO usrDAO = new UsuarioDAO();

        SimpleDateFormat frmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date dataatentimento = frmt.parse(rs.getString("dataatentimento"));
        Date dataconclusao = frmt.parse(rs.getString("dataconclusao"));

        prcr.setIdparecer(rs.getInt("idparecer"));
        prcr.setTecnico(usrDAO.getPorIdUsuario(rs.getInt("tecnico")));
        prcr.setDataatentimento(dataatentimento);
        prcr.setDataconclusao(dataconclusao);
        prcr.setParecer(rs.getString("parecer"));
        prcr.setChamado(rs.getInt("chamado"));


    }

    public Parecer getPorIdParecer(int id) throws ClassNotFoundException, SQLException, ParseException {
        this.db = new DataBase();

        PreparedStatement ps = (PreparedStatement) db.getPreparedStatement("SELECT * FROM NTE.parecer WHERE idchamado = ?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        Parecer parecer = new Parecer();
        UsuarioDAO usrDAO = new UsuarioDAO();

        SimpleDateFormat frmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date dataatentimento = frmt.parse(rs.getString("dataatentimento"));
        Date dataconclusao = frmt.parse(rs.getString("dataconclusao"));

        parecer.setIdparecer(rs.getInt("idparecer"));
        parecer.setTecnico(usrDAO.getPorIdUsuario(rs.getInt("tecnico")));
        parecer.setDataatentimento(dataatentimento);
        parecer.setDataconclusao(dataconclusao);
        parecer.setParecer(rs.getString("parecer"));
        parecer.setChamado(rs.getInt("chamado"));

        ps.close();
        rs.close();
        db.getCon().close();

        return parecer;
    }
}
