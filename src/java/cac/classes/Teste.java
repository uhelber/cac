/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.classes;

import cac.bean.UsuarioBean;
import cac.dao.ChamadoDAO;
import cac.dao.ParecerDAO;
import cac.dao.UsuarioDAO;
import cac.db.Chamado;
import cac.db.Parecer;
import cac.db.Usuario;
import java.sql.SQLException;




/**
 *
 * @author UhelberC
 */
public class Teste {

    public static void main(String args[]) throws ClassNotFoundException, SQLException{
        Parecer parecer = new Parecer();
        Usuario usr = new Usuario();
        Chamado chmd = new Chamado();
        ParecerDAO parecerDAO = new ParecerDAO();
        ChamadoDAO chmdDAO = new ChamadoDAO();
        UsuarioDAO usrDAO = new UsuarioDAO();
        UsuarioBean usrBean = new UsuarioBean();
        
        
        for(int i =0; i < usrBean.listarTodosPareceres().size(); i++){
            System.out.println(parecerDAO.getTodosPareceres().get(i).getParecer());
        }
    }
}
