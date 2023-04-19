package com.formulario;

/**
 * Classe base(bean) para o formulário de cliente.
 *
 * @author osmar
 */
public class ClienteFrm {

    private Integer clienteId;
    private String nome;
    private String cpf;
    private String mensagem;

    /**
     * Construtor sem parâmetros.
     */
    public ClienteFrm() {
        this(0, "", "", "");
    }

    /**
     * Construtor do formulário de cliente.
     *
     * @param clienteId
     * @param nome
     * @param cpf
     * @param mensagem
     */
    public ClienteFrm(Integer clienteId, String nome, String cpf, String mensagem) {
        this.clienteId = clienteId;
        this.nome = nome;
        this.cpf = cpf;
        this.mensagem = mensagem;
    }

    /**
     * @return the id
     */
    public Integer getClienteId() {
        return clienteId;
    }

    /**
     * @param clienteId Um clienteId a ser setado.
     */
    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * @return O nome de um cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome Um nome a ser setado.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return Um cpf.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf Um cpf.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return A mensagem de um formulário de cliente.
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem Uma mensagem a ser setada.
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return ("clienteId:" + getClienteId() + " - Nome :" + getNome() + " - CPF :" + getCpf());
    }
}
