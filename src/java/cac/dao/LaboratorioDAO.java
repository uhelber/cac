/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.dao;

import cac.db.DataBase;
import cac.db.Laboratorio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author UhelberC
 */
public class LaboratorioDAO {

    DataBase db;

    public LaboratorioDAO() throws ClassNotFoundException, SQLException {
    }

    public List<Laboratorio> getTodosLaboratorios() throws ClassNotFoundException, SQLException {
        this.db = new DataBase();

        List<Laboratorio> laboratorio = new LinkedList<Laboratorio>();
        ResultSet rs = this.db.getStatement().executeQuery("SELECT * FROM nte.laboratorio");
        while (rs.next()) {
            Laboratorio lab = new Laboratorio();
            polularListaLaboratorio(lab, rs);
            laboratorio.add(lab);
        }
        rs.close();
        db.getCon().close();

        return laboratorio;
    }

    private void polularListaLaboratorio(Laboratorio laboratorio, ResultSet rs) throws SQLException, ClassNotFoundException {
        laboratorio.setIdlaboratorio(rs.getInt("idlaboratorio"));
        laboratorio.setPregao(rs.getString("pregao"));
        laboratorio.setContrato(rs.getString("contrato"));
    }

    public Laboratorio getPorIdLaboratorio(int id) throws ClassNotFoundException, SQLException {
        this.db = new DataBase();
        
        
        PreparedStatement ps = (PreparedStatement) db.getPreparedStatement("SELECT * FROM nte.laboratorio WHERE 'idlaboratorio' = ?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        
        Laboratorio laboratorio = new Laboratorio();
        polularListaLaboratorio(laboratorio, rs);
        
        ps.close();
        rs.close();
        this.db.getCon().close();

        return laboratorio;
    }
}
