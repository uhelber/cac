/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.classes;

import cac.bean.UsuarioBean;
import cac.dao.ChamadoDAO;
import cac.dao.CidadeDAO;
import cac.dao.EscolaDAO;
import cac.dao.ParecerDAO;
import cac.dao.RegionalDAO;
import cac.dao.UsuarioDAO;
import cac.db.Chamado;
import cac.db.Cidade;
import cac.db.DataBase;
import cac.db.Escola;
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
        CidadeDAO dao = new CidadeDAO();
        EscolaDAO escDAO = new EscolaDAO();
        Cidade cid = dao.getPorIdCidade(1);
        Escola esc = escDAO.getPorIdCidade(cid);
        
        System.out.println(esc.getNome());

    }
}
