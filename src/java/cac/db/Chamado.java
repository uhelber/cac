/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cac.db;


/**
 *
 * @author UhelberC
 */
public class Chamado {
    private Integer idchamado;
    private String cidade;
    private String bairro;
    private String escola;
    private String contato;
    private String telefone;
    private Status status;
    private String descricao;
    private Integer abertopor;
    private String dataabertura;
    private String imagem;

    public Chamado() {
    }

    public Integer getIdchamado() {
        return idchamado;
    }

    public void setIdchamado(Integer idchamado) {
        this.idchamado = idchamado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getAbertopor() {
        return abertopor;
    }

    public void setAbertopor(Integer abertopor) {
        this.abertopor = abertopor;
    }

    public String getDataabertura() {
        return dataabertura;
    }

    public void setDataabertura(String dataabertura) {
        this.dataabertura = dataabertura;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    
}
