/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.classes;

import cac.bean.UsuarioBean;
import cac.dao.ChamadoDAO;
import cac.dao.ParecerDAO;
import cac.dao.RegionalDAO;
import cac.dao.UsuarioDAO;
import cac.db.Chamado;
import cac.db.DataBase;
import cac.db.Parecer;
import cac.db.Regional;
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
public class Teste {

    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        DataBase db = new DataBase();
        RegionalDAO regionalDAO = new RegionalDAO();

        PreparedStatement ps = (PreparedStatement) db.getPreparedStatement("SELECT * FROM nte.regional WHERE idregional = ?");
        ps.setInt(1, 1);

        ResultSet rs = ps.executeQuery();

        Regional regional = new Regional();
        if (rs.next()) {
            System.out.println(rs.getString("nome"));
        }

        ps.close();
        rs.close();
        db.getCon().close();

    }
}
