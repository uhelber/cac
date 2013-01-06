/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.dao;

import cac.db.Chamado;
import cac.db.DataBase;
import cac.db.Setor;
import cac.db.Status;
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
public class SetorDAO {

    DataBase db;
    private Setor setor;
    
    public SetorDAO(){
        this.setor = new Setor();
    }

    public Status getPorIdSetor(Integer id) throws ClassNotFoundException, SQLException {
        this.db = new DataBase();

        PreparedStatement ps = (PreparedStatement) db.getPreparedStatement("SELECT * FROM nte.setor WHERE idsetor = ?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        Status sts = new Status();

        if (rs.next()) {
            sts.setIdstatus(rs.getInt("idsetor"));
            sts.setNome(rs.getString("nome"));
        }

        ps.close();
        rs.close();
        db.getCon().close();

        return sts;
    }

    public List<Setor> getTodosSetor() throws ClassNotFoundException, SQLException {
        this.db = new DataBase();
        List<Setor> setor = new LinkedList<Setor>();

        ResultSet rs = db.getStatement().executeQuery("SELECT * FROM nte.setor");
        while (rs.next()) {
            Setor str = new Setor();
            polularListaChamado(str, rs);
            setor.add(str);
        }
        rs.close();
        db.getCon().close();

        return setor;
    }

    private void polularListaChamado(Setor stts, ResultSet rs) throws SQLException, ClassNotFoundException {
        stts.setIdsetor(rs.getInt("idsetor"));
        stts.setNome(rs.getString("nome"));
    }
}
