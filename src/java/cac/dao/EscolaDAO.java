/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.dao;

import cac.db.DataBase;
import cac.db.Escola;
import cac.db.Laboratorio;
import cac.db.Regional;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author UhelberC
 */
public class EscolaDAO {

    DataBase db;

    public EscolaDAO() throws ClassNotFoundException, SQLException {
    }

    public List<Escola> getTodosEscolas() throws ClassNotFoundException, SQLException {
        this.db = new DataBase();

        List<Escola> escola = new LinkedList<Escola>();
        ResultSet rs = this.db.getStatement().executeQuery("SELECT * FROM nte.escola");
        while (rs.next()) {
            Escola scl = new Escola();
            polularListaEscola(scl, rs);
            escola.add(scl);
        }
        rs.close();
        db.getCon().close();

        return escola;
    }

    private void polularListaEscola(Escola escola, ResultSet rs) throws SQLException, ClassNotFoundException {
        RegionalDAO regionalDAO = new RegionalDAO();
        Regional regional = regionalDAO.getPorIdRegional(rs.getInt("regional"));
        
        LaboratorioDAO laboratorioDAO = new LaboratorioDAO();
        Laboratorio laboratorio = laboratorioDAO.getPorIdLaboratorio(rs.getInt("laboratorio"));
        
        escola.setIdescola(rs.getInt("idescola"));
        escola.setRegional(regional);
        escola.setCidade(rs.getString("cidade"));
        escola.setInep(rs.getInt("inep"));
        escola.setEscola(rs.getString("escola"));
        escola.setEndereco(rs.getString("endereco"));
        escola.setBairro(rs.getString("bairro"));
        escola.setLaboratorio(laboratorio);
    }

    public Escola getPorIdEscola(int id) throws ClassNotFoundException, SQLException {
        this.db = new DataBase();
        
        
        PreparedStatement ps = (PreparedStatement) db.getPreparedStatement("SELECT * FROM nte.escola WHERE 'idescola' = ?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        
        Escola escola = new Escola();
        polularListaEscola(escola, rs);
        
        ps.close();
        rs.close();
        this.db.getCon().close();

        return escola;
    }
}
