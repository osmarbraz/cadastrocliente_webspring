package com.entidade;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestCliente {

    /**
     * Testa o construtor sem argumentos do cliente.
     */
    @Test
    void testCliente() {
        Cliente instancia = new Cliente();
        
        assertTrue((instancia.getClienteId() == 0) && "".equals(instancia.getNome()) && "".equals(instancia.getCpf()));
    }

    /**
     * Teste os sets do cliente.
     */
    @Test
    void testSetCliente() {
        Cliente instancia = new Cliente();
        instancia.setClienteId(1);
        instancia.setNome("A");
        instancia.setCpf("B");
        
        assertTrue((instancia.getClienteId() == 1) && "A".equals(instancia.getNome()) && "B".equals(instancia.getCpf()));
    }

    /**
     * Testa o to string.
     */
    @Test
    void testParaString() {
        Cliente instancia = new Cliente();
        String esperado = "clienteId:0 - Nome : - CPF :";
        
        assertEquals(esperado, instancia.toString());
    }
}
