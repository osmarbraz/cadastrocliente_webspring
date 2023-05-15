package com.servico;

import com.dao.ClienteDAO;
import com.entidade.Cliente;

import org.junit.jupiter.api.Test;
import static com.util.Util.nonNullCopyProperties;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
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
    private ClienteDAO dao;

    /**
     * Verifica se serviço foi carregado.
     *
     * @throws Exception
     */
    @Test
    void testCarregamentoServico() {
        assertThat(servico).isNotNull();
    }

    /**
     * Verifica se DAO foi carregado.
     *
     * @throws Exception
     */
    @Test
    void testCarregamentoDAO() {
        assertThat(dao).isNotNull();
    }

    /**
     * Testa a inserção de um cliente existente através do serviço.
     */
    @Test
    void testInserir() {
        //Instancia um cliente para testes
        Cliente clienteInserir = new Cliente(131, "Cliente Existente", "11111111111");
        doReturn(clienteInserir).when(dao).save(any());
        //Avalia o retorno
        assertTrue(servico.inserir(clienteInserir));
    }
       

    /**
     * Testa a alteração de um cliente existente através do serviço.
     */
    @Test
    void testAlterarClienteExistente() {
        //Instancia um cliente para alteração
        Cliente clienteInserir = new Cliente(131, "Cliente Existente", "11111111111");
        doReturn(clienteInserir).when(dao).save(any());
        //Insere o cliente a ser alterado
        servico.inserir(clienteInserir);

        //Recupera o cliente a ser alterado
        int retornoAlteracao = -1;
        doReturn(Optional.of(clienteInserir)).when(dao).findById(clienteInserir.getClienteId());
        Integer id = clienteInserir.getClienteId();
        Optional<Cliente> clienteAlterar = dao.findById(id);
        //Verifica se o cliente foi incluído
        if (clienteAlterar.isPresent()) {
            //Recupera o objeto
            Cliente oCliente = clienteAlterar.get();
            //Instancia um cliente para alteração
            Cliente aCliente = new Cliente(131, "Cliente Alterado", "2222222222");
            //Copia os dados do cliente a ser alterado para o cliente existente
            nonNullCopyProperties(aCliente, oCliente);
            //Salva a alteração
            dao.save(oCliente);
            //Retorno da alteração
            retornoAlteracao = servico.alterar(clienteInserir);
        }
        //Avalia o retorno
        assertSame(1, retornoAlteracao);
    }
    
    /**
     * Testa a alteração de um cliente inexistente através do serviço.
     */
    @Test
    void testAlterarClienteInexistente() {
        //Instancia um cliente para alteração
        Cliente clienteInserir = new Cliente(131, "Cliente Existente", "11111111111");
        doReturn(clienteInserir).when(dao).save(any());
        //Insere o cliente a ser alterado
        servico.inserir(clienteInserir);

        //Recupera o cliente a ser alterado
        int retornoAlteracao = -1;
        //Especifica um cliente inexistente
        doReturn(Optional.of(clienteInserir)).when(dao).findById(999);
        Integer id = 999;
        Optional<Cliente> clienteAlterar = dao.findById(id);
        //Verifica se o cliente foi incluído
        if (clienteAlterar.isPresent()) {
            //Recupera o objeto
            Cliente oCliente = clienteAlterar.get();
            //Instancia um cliente para alteração
            Cliente aCliente = new Cliente(131, "Cliente Alterado", "2222222222");
            //Copia os dados do cliente a ser alterado para o cliente existente
            nonNullCopyProperties(aCliente, oCliente);
            //Salva a alteração
            dao.save(oCliente);
            //Retorno da alteração
            retornoAlteracao = servico.alterar(clienteInserir);
        }
        //Avalia o retorno
        assertSame(-1, retornoAlteracao);
    }

    /**
     * Testa se um cliente existe.
     */
    @Test
    void testGetClienteIdExiste() {
        Cliente cliente = new Cliente(131, "TesteAlteracao", "11111111111");
        doReturn(Optional.of(cliente)).when(dao).findById(cliente.getClienteId());

        Cliente clienteRetorno = servico.getClientePeloId(cliente.getClienteId());
        //Avalia o retorno
        assertSame(clienteRetorno, cliente);
    }

    /**
     * Testa se um cliente não existe.
     */
    @Test
    void testGetClienteIdNaoExiste() {
        Cliente cliente = new Cliente(131, "TesteAlteracao", "11111111111");
        doReturn(Optional.of(cliente)).when(dao).findById(cliente.getClienteId());

        Cliente clienteRetorno = servico.getClientePeloId(141);
        //Avalia o retorno
        assertEquals(null, clienteRetorno);
    }
}