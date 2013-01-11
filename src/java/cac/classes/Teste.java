/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.classes;

import java.sql.SQLException;


/**
 *
 * @author UhelberC
 */
public class Teste {

    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        ConverteData cDT = new ConverteData();
        String dt = "2012-12-10";
        
        System.out.println(cDT.clu_Data(dt));

    }
}
