package com.servico;

import org.junit.jupiter.api.Test;
import com.dao.ClienteDAO;
import com.entidade.Cliente;
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
    
    @Test
    void testGetClienteIdExiste(){
        Cliente cliente = new Cliente(131, "TesteAlteracao", "11111111111");
        doReturn(Optional.of(cliente)).when(DAO).findById(cliente.getClienteId());

        Cliente clienteRetorno = servico.getClientePeloId(cliente.getClienteId());
        
        assertSame(clienteRetorno, cliente);
    }
    
    @Test
    void testGetClienteIdNaoExiste(){
        Cliente cliente = new Cliente(131, "TesteAlteracao", "11111111111");
        doReturn(Optional.of(cliente)).when(DAO).findById(cliente.getClienteId());

        Cliente clienteRetorno = servico.getClientePeloId(141);
        
        assertEquals(null, clienteRetorno);
    }
}
