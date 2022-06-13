package com.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Objeto modelo do sistema.
 * 
 * @author osmar
 */
@Entity
public class Cliente {

    @Id
    private Integer clienteId;

    private String nome;

    private String cpf;

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

    @Override
    public String toString() {
        return "Cleinte{"
                + "clienteId=" + clienteId
                + ", nome'" + nome + '\''
                + ", cpf='" + cpf + ')';
    }
}