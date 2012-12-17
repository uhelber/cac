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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author UhelberC
 */
public class ParecerDAO {
    
    Usuario usr;
    Chamado chmd;
    Parecer prcr;
    
    public boolean adicionarParecer(Parecer prcr) throws SQLException, ClassNotFoundException
    {
        DataBase db = new DataBase();
        
        PreparedStatement ps = (PreparedStatement) db.getPreparedStatement("INSERT INTO NTE.parecer VALUE (?, ?, ?, ?, ?, ?)");
        Date dt = new Date();
        SimpleDateFormat frmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        
        ps.setString(1, null);
        ps.setInt(2, prcr.getTecnico().getIdusuarios());
        ps.setString(3, frmt.format(dt));
        
        return false;
    }
    
}
