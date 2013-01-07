/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.db;


/**
 *
 * @author UhelberC
 */
public class Cidade {
    private Integer idcidade;
    private String nome;

    public Integer getIdcidade() {
        return idcidade;
    }

    public void setIdcidade(Integer idcidade) {
        this.idcidade = idcidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.idcidade != null ? this.idcidade.hashCode() : 0);
        hash = 29 * hash + (this.nome != null ? this.nome.hashCode() : 0);
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
        final Cidade other = (Cidade) obj;
        if (this.idcidade != other.idcidade && (this.idcidade == null || !this.idcidade.equals(other.idcidade))) {
            return false;
        }
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        return true;
    }
    
}