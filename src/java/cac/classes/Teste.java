/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.classes;

import cac.dao.ChamadoDAO;
import cac.dao.UsuarioDAO;
import cac.db.Chamado;
import cac.db.Usuario;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author UhelberC
 */
public class Teste {

    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        String oi = "uhelber";
        
        for(int i = 0; i < oi.length(); i++){
            System.out.println(oi.substring(i, i+1));
        }
    }
}
