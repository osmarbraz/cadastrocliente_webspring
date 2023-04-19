package com.entidade;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Objeto modelo do sistema.
 *
 * @author osmar
 */
@Entity
public class Cliente implements Serializable {

    @Id
    private Integer clienteId;
    private String nome;
    private String cpf;

    /**
     * Construtor sem argumentos da classe.
     */
    public Cliente() {
        this(0, "", "");
    }

    /**
     * Construtor com argumentos da classe.
     *
     * @param clienteId
     * @param nome
     * @param cpf
     */
    public Cliente(Integer clienteId, String nome, String cpf) {
        setClienteId(clienteId);
        setNome(nome);
        setCpf(cpf);
    }

    /**
     * @return o id
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

    @Override
    public String toString() {
        return ("clienteId:" + getClienteId() + " - Nome:" + getNome() + " - CPF:" + getCpf());
    }
}
