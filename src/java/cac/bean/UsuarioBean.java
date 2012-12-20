/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.bean;

import cac.classes.Mensagem;
import cac.dao.ChamadoDAO;
import cac.dao.ParecerDAO;
import cac.dao.StatusDAO;
import cac.dao.UsuarioDAO;
import cac.db.Chamado;
import cac.db.DataBase;
import cac.db.Parecer;
import cac.db.Status;
import cac.db.Usuario;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author UhelberC
 */
@ManagedBean
@SessionScoped
public class UsuarioBean {

    private UsuarioDAO usrDAO;
    private ChamadoDAO chmdDAO;
    private ParecerDAO prcrDAO;
    private Usuario usr = new Usuario();
    private Chamado chmd = new Chamado();
    private Parecer prcr = new Parecer();
    private StatusDAO sttsDAO = new StatusDAO();
    private Mensagem msn;
    private List<Status> status = new ArrayList<Status>();

    public UsuarioBean() throws ClassNotFoundException, SQLException {
        this.usrDAO = new UsuarioDAO();
        this.chmdDAO = new ChamadoDAO();
        this.status = this.verificarStatus();
    }

    public UsuarioDAO getUsrDAO() {
        return usrDAO;
    }

    public void setUsrDAO(UsuarioDAO usrDAO) {
        this.usrDAO = usrDAO;
    }

    public ChamadoDAO getChmdDAO() {
        return chmdDAO;
    }

    public void setChmdDAO(ChamadoDAO chmdDAO) {
        this.chmdDAO = chmdDAO;
    }

    public Usuario getUsr() {
        return usr;
    }

    public void setUsr(Usuario usr) {
        this.usr = usr;
    }

    public Chamado getChmd() {
        return chmd;
    }

    public void setChmd(Chamado chmd) {
        this.chmd = chmd;
    }

    public StatusDAO getSttsDAO() {
        return sttsDAO;
    }

    public void setSttsDAO(StatusDAO sttsDAO) {
        this.sttsDAO = sttsDAO;
    }

    public List<Status> getStatus() {
        return status;
    }

    public void setStatus(List<Status> status) {
        this.status = status;
    }

    public Parecer getPrcr() {
        return prcr;
    }

    public void setPrcr(Parecer prcr) {
        this.prcr = prcr;
    }

    /*
     * Área reservada a configurações de usuários.
     */
    public String validarUsuario() throws ClassNotFoundException, SQLException {
        this.msn = new Mensagem();
        String ir = "";

        if (this.usr != null) {
            this.usr = this.usrDAO.validarUsuario(this.usr.getUsuario(), this.usr.getSenha());
            ir = "consultachamado";
        } else {
            this.msn.EviarMensagens("frm:usuario", FacesMessage.SEVERITY_ERROR, "Erro na autenticação...", "Verifique se o usuario e senha estão certos.");
            return "index";
        }

        return ir;
    }

    public String nomeCadastrador() throws ClassNotFoundException, SQLException {
        Usuario usr = this.usrDAO.getPorIdUsuario(this.chmd.getAbertopor());

        return usr.getNome() + " " + usr.getSobrenome();
    }

    /*
     * Área reservada a configurações do chamado.
     */
    public String cadastrarChamado() throws ClassNotFoundException, SQLException {
        this.msn = new Mensagem();
        String ir = "";
        
        if (this.usr.getNome() != null) {
            System.out.println(usr.getNome());
            if (!this.chmd.getEscola().equals("")) {
                this.chmdDAO.adicionarChamado(this.chmd, this.usr);
                this.chmd = new Chamado();
                ir = "consultachamado";
            } else {
                this.msn.EviarMensagens("frm:escola", FacesMessage.SEVERITY_ERROR, "Atenção:", "Campo obrigatório");
            }
        } else {
            msn.EviarMensagens("frm:aviso", FacesMessage.SEVERITY_ERROR, "Erro na autenticação...", "Por favor, efetue login no sistema. Obrigado...");
            ir = "index";
        }

        return ir;
    }

    public String atualizarChamado() throws ClassNotFoundException, SQLException {
        String ir = "";

        if (this.usr.getNome() != null) {
            this.chmdDAO.atualizarChamado(this.chmd, this.prcr, this.usr);
            this.chmd = new Chamado();
            this.prcr = new Parecer();

            ir = "consultachamado";
        } else {
            msn.EviarMensagens("frm:aviso", FacesMessage.SEVERITY_ERROR, "Erro na autenticação...", "Por favor, efetue login no sistema. Obrigado...");
            ir = "index";
        }

        return ir;
    }

    public List<Chamado> listarTodosChamados() throws ClassNotFoundException, SQLException {
        LinkedList<Chamado> chamado = new LinkedList<Chamado>();
        if (this.usr.getNome() != null) {
            chamado = (LinkedList<Chamado>) this.chmdDAO.getTodosChamados();
        } else {
            this.sair();
        }

        return chamado;
    }

    public List<Parecer> listarTodosPareceres() throws SQLException, ClassNotFoundException {
        return this.prcrDAO.getTodosPareceresPorIdChamado(this.chmd.getIdchamado());
    }
    /*
     * Sistemas
     */

    public String irCadastrarChamado() {
        this.chmd = new Chamado();
        return "cadastrochamado";
    }

    public String irEditarChamado() {
        return "cadastrochamado";
    }

    public List<Status> verificarStatus() throws ClassNotFoundException, SQLException {
        List<Status> stts = this.sttsDAO.getTodosStatus();

        return stts;
    }

    public String sair() throws SQLException, ClassNotFoundException {
        this.usr = new Usuario();
        this.usrDAO = new UsuarioDAO();
        DataBase db = new DataBase();
        db.fecherTudo();

        return "index";
    }
}
