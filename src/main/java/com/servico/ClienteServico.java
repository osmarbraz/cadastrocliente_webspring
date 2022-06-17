package com.servico;

import com.entidade.Cliente;
import java.util.List;

/**
 * Interface que define as operações para a persistência de cliente.
 *
 * @author osmar
 */
public interface ClienteServico {

    public boolean inserir(Cliente cliente);

    public int alterar(Cliente cliente);

    public int excluir(Cliente cliente);

    public List<Cliente> getLista();

    public Cliente getClientePeloId(Integer clienteId);

    public Long count();
}
