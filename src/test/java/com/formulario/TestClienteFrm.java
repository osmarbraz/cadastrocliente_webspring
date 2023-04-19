package com.formulario;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestClienteFrm {

    /**
     * Testa o construtor sem argumentos do cliente.
     */
    @Test
    void testFrmClienteSemArgumentos() {
        ClienteFrm instancia = new ClienteFrm();      
        
        assertTrue((instancia.getClienteId()==0) && "".equals(instancia.getNome()) && "".equals(instancia.getCpf()) && "".equals(instancia.getMensagem()));
    }
    
    /**
     * Testa o construtor com argumentos do cliente.
     */
    @Test
    void testFrmClienteComArgumento() {
        ClienteFrm instancia = new ClienteFrm(1,"A","B","C");        
        
        assertTrue((instancia.getClienteId()==1) && "A".equals(instancia.getNome()) && "B".equals(instancia.getCpf()) && "C".equals(instancia.getMensagem()));
    }

    /**
     * Teste os sets do cliente.
     */
    @Test
    void testSetFrmCliente() {
        ClienteFrm instancia = new ClienteFrm();
        instancia.setClienteId(1);
        instancia.setNome("A");
        instancia.setCpf("B");
        instancia.setMensagem("C");
        
        assertTrue((instancia.getClienteId()==1) && "A".equals(instancia.getNome()) && "B".equals(instancia.getCpf()) && "C".equals(instancia.getMensagem()));
    }
    
    @Test
    void testParaString() {
        ClienteFrm instancia = new ClienteFrm();
        instancia.setClienteId(0);
        instancia.setNome("");
        instancia.setCpf("");
        String esperado = "clienteId:0 - Nome : - CPF :";
        
        assertEquals(esperado, instancia.toString());
    }   
}
