/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.db;


/**
 *
 * @author UhelberC
 */
public class Laboratorio {
    private Integer idlaboratorio;
    private String pregao;
    private String contrato;

    public Integer getIdlaboratorio() {
        return idlaboratorio;
    }

    public void setIdlaboratorio(Integer idlaboratorio) {
        this.idlaboratorio = idlaboratorio;
    }

    public String getPregao() {
        return pregao;
    }

    public void setPregao(String pregao) {
        this.pregao = pregao;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.idlaboratorio != null ? this.idlaboratorio.hashCode() : 0);
        hash = 37 * hash + (this.pregao != null ? this.pregao.hashCode() : 0);
        hash = 37 * hash + (this.contrato != null ? this.contrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Laboratorio other = (Laboratorio) obj;
        if (this.idlaboratorio != other.idlaboratorio && (this.idlaboratorio == null || !this.idlaboratorio.equals(other.idlaboratorio))) {
            return false;
        }
        if ((this.pregao == null) ? (other.pregao != null) : !this.pregao.equals(other.pregao)) {
            return false;
        }
        if ((this.contrato == null) ? (other.contrato != null) : !this.contrato.equals(other.contrato)) {
            return false;
        }
        return true;
    }
    
}
