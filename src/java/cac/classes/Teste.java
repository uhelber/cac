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


/**
 *
 * @author UhelberC
 */
public class Teste {

    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        ChamadoDAO chmdDAO = new ChamadoDAO();
        UsuarioDAO usrDAO = new UsuarioDAO();
        Chamado chmd = chmdDAO.getPorIdChamado(1);
        Usuario usr = usrDAO.getPorIdUsuario(2);
        
        

    }
}
