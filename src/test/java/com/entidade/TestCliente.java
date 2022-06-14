package com.entidade;

import com.entidade.Cliente;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class TestCliente {

    /**
     * Testa o construtor sem argumentos do cliente.
     */
    @Test
    void testCliente() {
        Cliente instancia = new Cliente();
        assertTrue((instancia.getClienteId()==0) && "".equals(instancia.getNome()) && "".equals(instancia.getCpf()));
    }

    @Test
    void testClienteIdInt() {
        Cliente instancia = new Cliente();
        instancia.setClienteId(1);        
        assertTrue((instancia.getClienteId()==1) && "".equals(instancia.getNome()) && "".equals(instancia.getCpf()));
    }

    @Test
    void testParaString() {
        Cliente instancia = new Cliente();
        String esperado = "clienteId:0 - Nome : - CPF :";
        assertEquals(esperado, instancia.toString());
    }
}
