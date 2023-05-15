package com.dao;

import com.entidade.Cliente;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
    ClienteDAO dao;

    /**
     * Verifica se dao foi carregado.
     *
     * @throws Exception
     */
    @Test
    void testCarregamentoDAO() {
        assertThat(dao).isNotNull();
    }

    /**
     * Testa a inserção de um cliente no SQLite.
     */
    @Test
    @Rollback(true)
    void testInsercao() {
        //Instancia um cliente para testes
        Cliente cliente = new Cliente(131, "Cliente Existente", "11111111111");
        Cliente clienteSalvo = dao.save(cliente);
        
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
        dao.save(cliente);
        //Consulta
        List<Cliente> lista = (List<Cliente>) dao.findAll();
        
        assertNotEquals(0, lista.size());
    }

    /**
     * Testa a alteração de um cliente existente no SQLite.
     */
    @Test
    @Rollback(true)
    void testAlteracaoNome() {
        Cliente cliente = new Cliente(131, "TesteAlteracao", "11111111111");
        dao.save(cliente);

        String nomeAlteracao = "Alterado";

        Cliente oCliente = dao.findById(cliente.getClienteId()).get();
        oCliente.setNome(nomeAlteracao);
        dao.save(oCliente);

        assertEquals(nomeAlteracao, oCliente.getNome());
    }

    /**
     * Testa a alteração de um cliente existente no SQLite.
     */
    @Test
    @Rollback(true)
    void testAlteracaoCpf() {
        Cliente cliente = new Cliente(131, "TesteAlteracao", "11111111111");
        dao.save(cliente);

        String cpfAlteracao = "22222222222";

        Cliente oCliente = dao.findById(cliente.getClienteId()).get();
        oCliente.setCpf(cpfAlteracao);
        dao.save(oCliente);

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
        dao.save(cliente);

        //Id do cliente a ser excluído
        Integer clienteId = 131;

        //Verifica se existe antes da exclusão
        boolean existeAntesExclusao = dao.findById(clienteId).isPresent();

        //Exclui o cliente        
        dao.deleteById(clienteId);

        //Verifica se existe depols da exclusão
        boolean naoExisteDepoisExclusao = dao.findById(clienteId).isPresent();

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
        dao.save(cliente);

        List clientes = (List<Cliente>) dao.findAll();

        assertThat(clientes).size().isPositive();
    }
}
