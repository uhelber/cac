/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.classes;

import cac.dao.ChamadoDAO;
import cac.dao.ParecerDAO;
import cac.dao.UsuarioDAO;
import cac.db.Chamado;
import cac.db.Parecer;
import cac.db.Usuario;
import java.sql.SQLException;
import java.text.ParseException;




/**
 *
 * @author UhelberC
 */
public class Teste {

    public static void main(String args[]) throws ClassNotFoundException, SQLException, ParseException{
        Parecer parecer = new Parecer();
        Usuario usr = new Usuario();
        Chamado chmd = new Chamado();
        ParecerDAO parecerDAO = new ParecerDAO();
        ChamadoDAO chmdDAO = new ChamadoDAO();
        UsuarioDAO usrDAO = new UsuarioDAO();
        
        
        for(int i =0; i < parecerDAO.getTodosPareceresPorIdChamado(9).size(); i++){
            System.out.println(parecerDAO.getTodosPareceres().get(i).getParecer());
        }
    }
}
