/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.db;

import java.util.Date;

/**
 *
 * @author UhelberC
 */
public class Parecer {
    private Integer idparecer;
    private Usuario tecnico;
    private Date dataatentimento;
    private Date dataconclusao;
    private String parecer;
    private Integer chamado;

    public Parecer() {
    }

    public Integer getIdparecer() {
        return idparecer;
    }

    public void setIdparecer(Integer idparecer) {
        this.idparecer = idparecer;
    }

    public Usuario getTecnico() {
        return tecnico;
    }

    public void setTecnico(Usuario tecnico) {
        this.tecnico = tecnico;
    }

    public Date getDataatentimento() {
        return dataatentimento;
    }

    public void setDataatentimento(Date dataatentimento) {
        this.dataatentimento = dataatentimento;
    }

    public Date getDataconclusao() {
        return dataconclusao;
    }

    public void setDataconclusao(Date dataconclusao) {
        this.dataconclusao = dataconclusao;
    }

    public String getParecer() {
        return parecer;
    }

    public void setParecer(String parecer) {
        this.parecer = parecer;
    }

    public Integer getChamado() {
        return chamado;
    }

    public void setChamado(Integer chamado) {
        this.chamado = chamado;
    }

}
