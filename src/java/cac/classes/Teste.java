/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.classes;

import cac.bean.UsuarioBean;
import cac.dao.EscolaDAO;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author UhelberC
 */
public class Teste {

    public static void main(String args[]) throws ClassNotFoundException, SQLException, ParseException {
        EscolaDAO bean = new EscolaDAO();
        System.out.println(bean.getTodosEscolas().size());
        
        for (int i = 0; i < bean.getTodosEscolas().size(); i++) {
            System.out.println(bean.getTodosEscolas().get(i).getNome());
        }
    }
}
