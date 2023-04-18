package com.servico;

import org.junit.jupiter.api.Test;
import com.dao.ClienteDAO;
import com.entidade.Cliente;
import static com.util.Util.nonNullCopyProperties;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class TestClienteServico {

    /**
     * Autowire no serviço que queremos testar
     */
    @Autowired
    private ClienteServico servico;

    /**
     * Crie uma implementação simulada do DAO
     */
    @MockBean
    private ClienteDAO DAO;

    /**
     * Testa a inserção de um cliente existente através do serviço.
     */
    @Test
    void testInserir() {
        //Instancia um cliente para testes
        Cliente cliente = new Cliente(131, "Cliente Existente", "11111111111");
        doReturn(cliente).when(DAO).save(any());

        assertTrue(servico.inserir(cliente));
    }

    /**
     * Testa a alteração de um cliente existente através do serviço.
     */
    @Test
    void testAlterar() {
        //Instancia um cliente para alteração
        Cliente clienteInserir = new Cliente(131, "Cliente Existente", "11111111111");
        doReturn(clienteInserir).when(DAO).save(any());
        //Insere o cliente a ser alterado
        servico.inserir(clienteInserir);

        //Recupera o cliente a ser alterado
        int retornoAlteracao = -1;
        doReturn(Optional.of(clienteInserir)).when(DAO).findById(clienteInserir.getClienteId());
        Integer id = clienteInserir.getClienteId();
        Optional<Cliente> clienteAlterar = DAO.findById(id);
        //Verifica se o cliente foi incluído
        if (clienteAlterar.isPresent()) {
            //Recupera o objeto
            Cliente oCliente = clienteAlterar.get();
            //Instancia um cliente para alteração
            Cliente aCliente = new Cliente(131, "Cliente Alterado", "2222222222");
            //Copia os dados do cliente a ser alterado para o cliente existente
            nonNullCopyProperties(aCliente, oCliente);
            //Salva a alteração
            DAO.save(oCliente);
            //Retorno da alteração
            retornoAlteracao = servico.alterar(clienteInserir);
        }
        assertSame(1, retornoAlteracao);
    }

    @Test
    void testGetClienteIdExiste() {
        Cliente cliente = new Cliente(131, "TesteAlteracao", "11111111111");
        doReturn(Optional.of(cliente)).when(DAO).findById(cliente.getClienteId());

        Cliente clienteRetorno = servico.getClientePeloId(cliente.getClienteId());

        assertSame(clienteRetorno, cliente);
    }

    @Test
    void testGetClienteIdNaoExiste() {
        Cliente cliente = new Cliente(131, "TesteAlteracao", "11111111111");
        doReturn(Optional.of(cliente)).when(DAO).findById(cliente.getClienteId());

        Cliente clienteRetorno = servico.getClientePeloId(141);

        assertEquals(null, clienteRetorno);
    }
}
