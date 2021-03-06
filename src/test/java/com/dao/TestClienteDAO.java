package com.dao;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

import java.util.List;

import com.entidade.Cliente;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class TestClienteDAO {

    @Autowired
    ClienteDAO DAO;
        
    @Test
    @Rollback(true)
    void testInsercao(){
        //Instancia um cliente para testes
        Cliente cliente = new Cliente(131, "Cliente Existente", "11111111111");
        Cliente clienteSalvo = DAO.save(cliente);
        assertNotNull(clienteSalvo);
    }
    
    /**
     * Testa um cliente existente no SQLite.
     */
    @Test
    @Rollback(true)
    void testConsulta() {      
        //Instancia um cliente para testes
        Cliente cliente = new Cliente(131, "Cliente Existente", "11111111111");
        // Insere os dados da consulta
        DAO.save(cliente);
        //Consulta
        List<Cliente> lista = (List<Cliente>) DAO.findAll();                
        assertNotEquals(0, lista.size());
    }
            
    /**
     * Testa a alteração de um cliente existente no SQLite.
     */
    @Test
    @Rollback(true)
    void testAlteracaoNome() {
        Cliente cliente = new Cliente(131, "TesteAlteracao", "11111111111");
        DAO.save(cliente);  
        
        String nomeAlteracao = "Alterado";
        
        Cliente oCliente = DAO.findById(cliente.getClienteId()).get();
        oCliente.setNome(nomeAlteracao);
        DAO.save(oCliente);  
       
        assertEquals(nomeAlteracao, oCliente.getNome());
    }
                
    /**
     * Testa a alteração de um cliente existente no SQLite.
     */
    @Test
    @Rollback(true)
    void testAlteracaoCpf() {
        Cliente cliente = new Cliente(131, "TesteAlteracao", "11111111111");
        DAO.save(cliente);  
        
        String cpfAlteracao = "22222222222";
        
        Cliente oCliente = DAO.findById(cliente.getClienteId()).get();
        oCliente.setCpf(cpfAlteracao);
        DAO.save(oCliente);  
       
        assertEquals(cpfAlteracao, oCliente.getCpf());
    }
    
    /**
     * Testa a exclusão de um cliente existente no SQLite.
     */
    @Test
    @Rollback(true)
    void testExclusao() {
        //Inclui um cliente para realizar o testes
        Cliente cliente = new Cliente(131, "TesteAlteracao", "11111111111");
        DAO.save(cliente);  
        
        //Id do cliente a ser excluído
        Integer clienteId = 131;
                
        //Verifica se existe antes da exclusão
        boolean existeAntesExclusao = DAO.findById(clienteId).isPresent();
        
        //Exclui o cliente        
        DAO.deleteById(clienteId);  
        
        //Verifica se existe depols da exclusão
        boolean naoExisteDepoisExclusao = DAO.findById(clienteId).isPresent();
                        
        assertTrue(existeAntesExclusao);
        assertFalse(naoExisteDepoisExclusao);
    }
    
     /**
     * Testa a exclusão de um cliente existente no SQLite.
     */
    @Test
    @Rollback(true)
    void testLista() {
        //Inclui um cliente para realizar o testes
        Cliente cliente = new Cliente(131, "TesteAlteracao", "11111111111");
        DAO.save(cliente);  
        
        List clientes = (List<Cliente>) DAO.findAll();
        
        assertThat(clientes).size().isPositive();
    }
}
