package com.servico;

import com.dao.ClienteDAO;
import com.entidade.Cliente;

import org.junit.jupiter.api.Test;
import static com.util.Util.nonNullCopyProperties;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
     * Testa a inserção de um cliente através do serviço.
     */
    @Test
    void testInserirSucesso() {
        //Instancia um cliente para testes
        Cliente clienteInserir = new Cliente(131, "Cliente Existente", "11111111111");
        //doReturn(clienteInserir).when(dao).save(any());
         when(dao.save(clienteInserir)).thenReturn(clienteInserir);
        
        //Chama o método do serviço
        boolean retorno = servico.inserir(clienteInserir);
        
        //Avalia o retorno
        assertTrue(retorno);
        verify(dao, times(1)).save(clienteInserir);
    }
    
     /**
     * Testa a inserção de um cliente com falha através do serviço.
     */
    @Test
    void testInserirFalha() {
        //Instancia um cliente para testes
        Cliente clienteInserir = new Cliente(131, "Cliente Existente", "11111111111");
        //doReturn(clienteInserir).when(dao).save(any());
         when(dao.save(clienteInserir)).thenReturn(null);
        
        //Chama o método do serviço
        boolean retorno = servico.inserir(clienteInserir);
        
        //Avalia o retorno
        assertFalse(retorno);
        verify(dao, times(1)).save(clienteInserir);
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
            
            //Chama o método do serviço
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
            
            //Chama o método do serviço
            retornoAlteracao = servico.alterar(clienteInserir);
        }
        //Avalia o retorno
        assertSame(-1, retornoAlteracao);
    }
    
     /**
     * Testa a exclusão de um cliente existente através do serviço.
     */        
    @Test
    void testExcluirExistente() {
        //Instancia um cliente para testes
        Cliente clienteInserir = new Cliente(139, "Cliente Existente", "11111111111");
        when(dao.existsById(clienteInserir.getClienteId())).thenReturn(true);
         
        //Chama o método do serviço
        int retorno = servico.excluir(clienteInserir);        
        //Avalia o retorno
        assertEquals(1,retorno);
        //Verifica se existe
        verify(dao).existsById(clienteInserir.getClienteId());
        //Verifica se o cliente foi excluído
        verify(dao).deleteById(clienteInserir.getClienteId());
    }
     
    /**
     * Testa a exclusão de um cliente inexistente através do serviço.
     */        
    @Test
    void testExcluirInexistente() {
        //Instancia um cliente para testes
        Cliente clienteInserir1 = new Cliente(139, "Cliente Existente", "11111111111");
        Cliente clienteInserir2 = new Cliente(140, "Cliente Inexistente", "22222222222");
        when(dao.existsById(clienteInserir1.getClienteId())).thenReturn(true);
        
        //Chama o método do serviço                 
        int retorno = servico.excluir(clienteInserir2);
        //Avalia o retorno
        assertEquals(0,retorno);
        //Verifica se existe
        verify(dao).existsById(clienteInserir2.getClienteId());        
    }

    /**
     * Testa se um cliente existe.
     */
    @Test
    void testGetClienteIdExiste() {
        //Cliente para testar a existência
        Cliente cliente = new Cliente(131, "TesteAlteracao", "11111111111");
        doReturn(Optional.of(cliente)).when(dao).findById(cliente.getClienteId());       
        
        //Chama o método do serviço
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
        
        //Chama o método do serviço
        Cliente clienteRetorno = servico.getClientePeloId(141);
        //Avalia o retorno
        assertEquals(null, clienteRetorno);
    }    
    
     /**
     * Testa a lista de clientes.
     */
    @Test
    void testGetLista() {
        // Cria a lista de cliente
        List<Cliente> listaClientes = Arrays.asList(new Cliente(131, "TesteClienteLista1", "11111111111"), new Cliente(132, "TesteClienteLista2", "2222222222"));
        when(dao.findAll()).thenReturn(listaClientes);
        
        //Chama o método do serviço
        List<Cliente> resultado = servico.getLista();        
        //Avalia as listas
        assertEquals(listaClientes, resultado);
        verify(dao).findAll();
    }    
    
    /**
     * Testa a contagem de clientes.
     */
    @Test
    void testCount() {
        //Cria a lista com 5 elementos
        when(dao.count()).thenReturn(5L);
        //Chama o método do serviço
        Long retorno = servico.count();
        
        //Avalia o retorno
        assertEquals(5L, retorno);
        verify(dao).count();
    }
}