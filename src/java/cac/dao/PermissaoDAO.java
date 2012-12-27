/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.dao;

import cac.db.DataBase;
import cac.db.Permissao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author UhelberC
 */
public class PermissaoDAO{
    
    DataBase db;
    
    public PermissaoDAO() throws ClassNotFoundException, SQLException {
        
    }
    
    public Permissao getPorIdPermissao(Integer id) throws ClassNotFoundException, SQLException {
        this.db = new DataBase();
        
        PreparedStatement ps = (PreparedStatement) db.getPreparedStatement("SELECT * FROM nte.permissoes WHERE idpermissao = ?");
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        Permissao permissao = new Permissao();

        if (rs.next()) {
            permissao.setIdpermissao(rs.getInt("idpermissao"));
            permissao.setTipo(rs.getString("tipo"));
        }

        ps.close();
        rs.close();
        db.getCon().close();

        return permissao;
    }
    
}
