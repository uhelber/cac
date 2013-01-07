/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.db;


/**
 *
 * @author UhelberC
 */
public class Regional {
    private Integer idregional;
    private String nome;

    public Integer getIdregional() {
        return idregional;
    }

    public void setIdregional(Integer idregional) {
        this.idregional = idregional;
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
        hash = 41 * hash + (this.idregional != null ? this.idregional.hashCode() : 0);
        hash = 41 * hash + (this.nome != null ? this.nome.hashCode() : 0);
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
        final Regional other = (Regional) obj;
        if (this.idregional != other.idregional && (this.idregional == null || !this.idregional.equals(other.idregional))) {
            return false;
        }
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        return true;
    }
    
}
