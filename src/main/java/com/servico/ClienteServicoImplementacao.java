package com.servico;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

import static com.util.Util.nonNullCopyProperties;
import com.dao.ClienteDAO;
import com.entidade.Cliente;

/**
 * Implementação dos serviços de cliente.
 *
 * @author osmar
 */
@Service
public class ClienteServicoImplementacao implements ClienteServico {

    @Autowired
    private final ClienteDAO clienteDAO;
    
     public ClienteServicoImplementacao(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @Override
    public boolean inserir(Cliente cliente) {
        return clienteDAO.save(cliente) != null;
    }

    @Override
    public int alterar(Cliente cliente) {
        Integer id = cliente.getClienteId();        
        Optional<Cliente> clienteAlterar = clienteDAO.findById(id);
        if (clienteAlterar.isPresent()) {
            Cliente oCliente = clienteAlterar.get();
            nonNullCopyProperties(cliente, oCliente);
            clienteDAO.save(oCliente);
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public int excluir(Cliente cliente) {
        int clienteId = cliente.getClienteId();
        //Se o cliente existe
        if (clienteDAO.existsById(clienteId)) {
            clienteDAO.deleteById(clienteId);
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Cliente getClientePeloId(Integer clienteId) {
        Optional<Cliente> cliente = clienteDAO.findById(clienteId);
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            return null;
        }
    }

    @Override
    public List<Cliente> getLista() {
        return (List<Cliente>) clienteDAO.findAll();
    }

    @Override
    public Long count() {
        return clienteDAO.count();
    }
}
