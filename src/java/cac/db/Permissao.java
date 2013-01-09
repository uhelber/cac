/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.db;

import java.io.Serializable;

/**
 *
 * @author UhelberC
 */
public class Permissao implements Serializable {
    private Integer idpermissao;
    private String tipo;

    public Permissao() {
    }

    public Integer getIdpermissao() {
        return idpermissao;
    }

    public void setIdpermissao(Integer idpermissao) {
        this.idpermissao = idpermissao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
