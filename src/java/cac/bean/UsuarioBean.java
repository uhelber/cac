/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.bean;

import cac.classes.Mensagem;
import cac.dao.ChamadoDAO;
import cac.dao.CidadeDAO;
import cac.dao.EscolaDAO;
import cac.dao.FuncaoDAO;
import cac.dao.ParecerDAO;
import cac.dao.PermissaoDAO;
import cac.dao.SetorDAO;
import cac.dao.StatusDAO;
import cac.dao.UsuarioDAO;
import cac.db.Chamado;
import cac.db.Cidade;
import cac.db.DataBase;
import cac.db.Escola;
import cac.db.Funcao;
import cac.db.Parecer;
import cac.db.Permissao;
import cac.db.Setor;
import cac.db.Status;
import cac.db.Usuario;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author UhelberC
 */
@ManagedBean
@SessionScoped
public class UsuarioBean {
    /*
     * DAOs
     */

    private UsuarioDAO usrDAO;
    private ChamadoDAO chmdDAO;
    private ParecerDAO prcrDAO;
    private StatusDAO sttsDAO = new StatusDAO();
    private EscolaDAO escolaDAO;
    private CidadeDAO cidadeDAO;
    private SetorDAO setorDAO;
    private FuncaoDAO funcaoDAO;
    private PermissaoDAO permissaoDAO;
    /*
     * Objetos
     */
    private Usuario usr = new Usuario();
    private Usuario novoUsr = new Usuario();
    private Chamado chmd = new Chamado();
    private Parecer prcr = new Parecer();
    private Escola escola = new Escola();
    private Cidade cidade = new Cidade();
    private Setor setor;
    private Funcao funcao;
    private Cidade cidadeSeleciona = null;
    /*
     * Argumentos
     */
    private Mensagem msn;
    private String organizar = null;
    private String tipoListarChamados = null;
    private String statusFinalizado = null;
    private String confirmarSenha = "";

    public UsuarioBean() throws ClassNotFoundException, SQLException {
        this.usrDAO = new UsuarioDAO();
        this.chmdDAO = new ChamadoDAO();
        this.prcrDAO = new ParecerDAO();
        this.escolaDAO = new EscolaDAO();
        this.cidadeDAO = new CidadeDAO();
        this.setorDAO = new SetorDAO();
        this.funcaoDAO = new FuncaoDAO();
        this.permissaoDAO = new PermissaoDAO();

        this.organizar = null;
        this.tipoListarChamados = null;
        this.statusFinalizado = null;
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

    public Parecer getPrcr() {
        return prcr;
    }

    public void setPrcr(Parecer prcr) {
        this.prcr = prcr;
    }

    public String getOrganizar() {
        return organizar;
    }

    public void setOrganizar(String organizar) {
        this.organizar = organizar;
    }

    public String getTipoListarChamados() {
        return tipoListarChamados;
    }

    public void setTipoListarChamados(String tipoListarChamados) {
        this.tipoListarChamados = tipoListarChamados;
    }

    public String getStatusFinalizado() {
        return statusFinalizado;
    }

    public void setStatusFinalizado(String statusFinalizado) {
        this.statusFinalizado = statusFinalizado;
    }

    public Usuario getNovoUsr() {
        return novoUsr;
    }

    public void setNovoUsr(Usuario novoUsr) {
        this.novoUsr = novoUsr;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Cidade getCidadeSeleciona() {
        return cidadeSeleciona;
    }

    public void setCidadeSeleciona(Cidade cidadeSeleciona) {
        this.cidadeSeleciona = cidadeSeleciona;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }


    /*
     * Área reservada a configurações de usuários.
     */
    public String validarUsuario() throws ClassNotFoundException, SQLException {
        this.msn = new Mensagem();
        String ir = "";

        if (this.usr != null) {
            Usuario usuario = this.usrDAO.validarUsuario(this.usr.getUsuario(), this.usr.getSenha());

            if ((usuario != null)) {
                this.usr = usuario;

                ir = "listarchamados";
            } else {
                this.msn.EviarMensagens("frm:usuario", FacesMessage.SEVERITY_ERROR, "Erro na autenticação...", "Verifique se o usuario e senha estão certos.");
                ir = "index";
            }
        } else {
            this.msn.EviarMensagens("frm:usuario", FacesMessage.SEVERITY_ERROR, "Erro na autenticação...", "Verifique se o usuario e senha estão certos.");
            ir = "index";
        }

        return ir;
    }

    public String nomeCadastrador() throws ClassNotFoundException, SQLException {
        Usuario usr = this.usrDAO.getPorIdUsuario(this.chmd.getAbertopor());

        return usr.getNome() + " " + usr.getSobrenome();
    }
    /*
     * Área reservada a configurações do usuários.
     */

    public String cadastrarUsuario() throws ClassNotFoundException, SQLException {
        String ir = "";
        Integer teste;
        this.novoUsr.setCadastrador(this.usr.getIdusuarios());

        if (this.usr.getNome() != null) {
            if (this.novoUsr.getSenha().equals(this.confirmarSenha)) {
                teste = this.usrDAO.verificarUsuarioJaCadastrado(this.novoUsr);
                if (teste == 0) {
                    this.novoUsr = new Usuario();
                    ir = "cadastrarusuario";
                }
            } else {
                msn.EviarMensagens("frm:senha", FacesMessage.SEVERITY_ERROR, "", "Senhas não coincidem, tente outra vez...");
                ir = "cadastrarusuario";
            }
        } else {
            msn.EviarMensagens("frm:aviso", FacesMessage.SEVERITY_ERROR, "Erro na autenticação...", "Por favor, efetue login no sistema. Obrigado...");
            ir = "index";
        }

        return ir;
    }


    /*
     * Área reservada a configurações do chamado.
     */
    public String cadastrarChamado() throws ClassNotFoundException, SQLException {
        this.msn = new Mensagem();
        String ir = "";

        if (this.usr.getNome() != null) {
            if (this.chmd.getEscola().getCidade() != null) {
                this.chmdDAO.adicionarChamado(this.chmd, this.usr);
                this.chmd = new Chamado();
                this.cidade = new Cidade();
                ir = "listarchamados";
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
        String ir;

        if (this.usr.getNome() != null) {
            if (!this.prcr.getParecer().equals("")) {
                this.chmdDAO.atualizarChamado(this.chmd, this.prcr, this.usr);
                this.prcr = new Parecer();
                msn.EviarMensagens("frm:parecer", FacesMessage.SEVERITY_INFO, "Atualização realizada com sucesso!!!", "");
            } else {
                msn.EviarMensagens("frm:parecer", FacesMessage.SEVERITY_ERROR, "Não se pode atualizar o chamado sem haver um parecer...", "");
            }

            ir = "editarchamado";
        } else {
            msn.EviarMensagens("frm:aviso", FacesMessage.SEVERITY_ERROR, "Erro na autenticação...", "Por favor, efetue login no sistema. Obrigado...");
            ir = "index";
        }

        return ir;
    }

    public List<Chamado> listarTodosChamados() throws ClassNotFoundException, SQLException {
        List<Chamado> chamado = new LinkedList<Chamado>();
        if (this.usr.getNome() != null) {
            chamado = (LinkedList<Chamado>) this.chmdDAO.getTodosChamados(this.tipoListarChamados, this.organizar);
        } else {
            this.sair();
        }

        return chamado;
    }

    public List<Parecer> listarTodosPareceres() throws SQLException, ClassNotFoundException {
        List<Parecer> parecer = new LinkedList<Parecer>();
        if (this.usr.getNome() != null) {
            parecer = (LinkedList<Parecer>) this.prcrDAO.getTodosPareceresPorIdChamado(this.chmd.getIdchamado());
        }
        return parecer;

    }

    public List<Escola> listarTodosEscola() throws SQLException, ClassNotFoundException {
        List<Escola> escola = new LinkedList<Escola>();
        System.out.println(this.escola.getCidade().getIdcidade());
        if (this.usr.getNome() != null) {
            escola = (List<Escola>) this.escolaDAO.getTodosEscolas();
        }
        return escola;

    }

    public List<Escola> listarTodosEscolaPorIdCidade() throws SQLException, ClassNotFoundException {
        List<Escola> escola = new LinkedList<Escola>();

        if (this.usr.getNome() != null) {
            if (this.cidadeSeleciona != null) {
                escola = (List<Escola>) this.escolaDAO.getTodosEscolasPorIdCidade(this.cidadeSeleciona);
            }
        }

        return escola;

    }

    public List<Cidade> listarTodosCidade() throws SQLException, ClassNotFoundException {
        List<Cidade> cidade = new LinkedList<Cidade>();

        if (this.usr.getNome() != null) {
            cidade = (LinkedList<Cidade>) this.cidadeDAO.getTodosCidades();
        }
        return cidade;

    }

    public List<Setor> listarTodosSetores() throws SQLException, ClassNotFoundException {
        List<Setor> setor = new LinkedList<Setor>();

        if (this.usr.getNome() != null) {
            setor = (LinkedList<Setor>) this.setorDAO.getTodosSetor();
        }
        return setor;

    }

    public List<Funcao> listarTodosFuncaos() throws SQLException, ClassNotFoundException {
        List<Funcao> funcao = new LinkedList<Funcao>();

        if (this.usr.getNome() != null) {
            funcao = (LinkedList<Funcao>) this.funcaoDAO.getTodosFuncao();
        }
        return funcao;

    }

    public List<Permissao> listarTodosPermissoes() throws SQLException, ClassNotFoundException {
        List<Permissao> permissao = new LinkedList<Permissao>();

        if (this.usr.getNome() != null) {
            permissao = (LinkedList<Permissao>) this.permissaoDAO.getTodosPermissoes(this.usr);
        }
        return permissao;

    }

    public List<Usuario> listarTodosUsuarios() throws SQLException, ClassNotFoundException {
        List<Usuario> usuario = new LinkedList<Usuario>();

        if (this.usr.getNome() != null) {
            if (this.usr.getPermissao().getIdpermissao() != 1) {
                usuario = (LinkedList<Usuario>) this.usrDAO.getTodosUsuarios();
            }
            else{
                usuario = null;
            }
        }

        return usuario;
    }

    /*
     * Sistemas
     */
    @PreDestroy
    public void destroy() {
    }

    public String irCadastrarChamado() {
        this.chmd = new Chamado();
        this.organizar = null;
        return "cadastrarchamado";
    }

    public String irEditarChamado() {
        this.organizar = null;

        if (this.chmd.getStatus().getIdstatus() == 7) {
            this.statusFinalizado = "finalizado";
        } else {
            this.statusFinalizado = null;
        }

        return "editarchamado";
    }

    public List<Status> verificarStatus() throws ClassNotFoundException, SQLException {
        List<Status> stts = this.sttsDAO.getTodosStatusPorPermissao(this.usr, this.chmd);

        return stts;
    }

    public String sair() throws SQLException, ClassNotFoundException {
        this.usr = new Usuario();
        this.usrDAO = new UsuarioDAO();
        DataBase db = new DataBase();
        db.fecherTudo();

        return "index";
    }

    public String dataAtual() {
        Date dt = new Date();
        SimpleDateFormat frmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return frmt.format(dt);
    }

    public String voltar() {
        if (this.tipoListarChamados.equals("finalizado")) {
            this.organizar = null;
            this.tipoListarChamados = null;
            this.statusFinalizado = null;
        }

        return "listarchamados";
    }

    public String irChamadosFinalizados() {
        this.organizar = "finalizado";
        this.tipoListarChamados = "finalizado";

        return "chamadosfinalizados";
    }

    public String irListarChamados() {
        this.organizar = null;
        this.tipoListarChamados = null;
        this.statusFinalizado = null;

        return "listarchamados";
    }

    public String organizarNull() {
        if (this.tipoListarChamados != null) {
            return this.irChamadosFinalizados();
        } else {
            return this.irListarChamados();
        }
    }

    public void selecionarCidade(ValueChangeEvent evento) {
        this.cidadeSeleciona = (Cidade) evento.getNewValue();
    }
}
