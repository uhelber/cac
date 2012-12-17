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
    DataBase db;
    Usuario usr;
    Chamado chmd;
    Parecer prcr;
    
    public boolean adicionarParecer(Chamado chmd, Parecer parecer, Usuario usr) throws SQLException, ClassNotFoundException
    {
        Date dt = new Date();
        SimpleDateFormat frmt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        
        PreparedStatement ps = (PreparedStatement) db.getPreparedStatement("INSERT INTO parecer VALUE (?, ?, ?, ?, ?, ?)");
        ps.setString(1, null);
        /*
        ps.setInt(2, usr.getIdusuarios());
        ps.setString(3, frmt.format(dt));
        if(chmd.getStatus().getIdstatus() == 7)
        {
            ps.setString(4, frmt.format(dt));
        }
        else{
            ps.setString(4, null);
        }
        ps.setString(5, parecer.getParecer());
        ps.setInt(6, chmd.getIdchamado());
        */
        boolean retorno = ps.execute();
        ps.close();
        db.getCon().close();

        return retorno;
    }
    
    
}
