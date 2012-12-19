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
    private String dataatentimento;
    private String dataconclusao;
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

    public String getDataatentimento() {
        return dataatentimento;
    }

    public void setDataatentimento(String dataatentimento) {
        this.dataatentimento = dataatentimento;
    }

    public String getDataconclusao() {
        return dataconclusao;
    }

    public void setDataconclusao(String dataconclusao) {
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
