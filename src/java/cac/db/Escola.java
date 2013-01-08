/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.db;


/**
 *
 * @author UhelberC
 */
public class Escola {
    private Integer idescola;
    private Regional regional;
    private Cidade cidade;
    private Integer inep;
    private String nome;
    private String endereco;
    private String bairro;
    private Laboratorio laboratorio;

    public Integer getIdescola() {
        return idescola;
    }

    public void setIdescola(Integer idescola) {
        this.idescola = idescola;
    }

    public Regional getRegional() {
        return regional;
    }

    public void setRegional(Regional regional) {
        this.regional = regional;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Integer getInep() {
        return inep;
    }

    public void setInep(Integer inep) {
        this.inep = inep;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.idescola != null ? this.idescola.hashCode() : 0);
        hash = 89 * hash + (this.regional != null ? this.regional.hashCode() : 0);
        hash = 89 * hash + (this.cidade != null ? this.cidade.hashCode() : 0);
        hash = 89 * hash + (this.inep != null ? this.inep.hashCode() : 0);
        hash = 89 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 89 * hash + (this.endereco != null ? this.endereco.hashCode() : 0);
        hash = 89 * hash + (this.bairro != null ? this.bairro.hashCode() : 0);
        hash = 89 * hash + (this.laboratorio != null ? this.laboratorio.hashCode() : 0);
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
        final Escola other = (Escola) obj;
        if (this.idescola != other.idescola && (this.idescola == null || !this.idescola.equals(other.idescola))) {
            return false;
        }
        if (this.regional != other.regional && (this.regional == null || !this.regional.equals(other.regional))) {
            return false;
        }
        if (this.cidade != other.cidade && (this.cidade == null || !this.cidade.equals(other.cidade))) {
            return false;
        }
        if (this.inep != other.inep && (this.inep == null || !this.inep.equals(other.inep))) {
            return false;
        }
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        if ((this.endereco == null) ? (other.endereco != null) : !this.endereco.equals(other.endereco)) {
            return false;
        }
        if ((this.bairro == null) ? (other.bairro != null) : !this.bairro.equals(other.bairro)) {
            return false;
        }
        if (this.laboratorio != other.laboratorio && (this.laboratorio == null || !this.laboratorio.equals(other.laboratorio))) {
            return false;
        }
        return true;
    }

}
