package com.formulario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

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

    @Test
    void testClienteIdInt() {
        ClienteFrm instancia = new ClienteFrm();
        instancia.setClienteId(1);
        instancia.setNome("");
        instancia.setCpf("");
        assertTrue((instancia.getClienteId()==1) && "".equals(instancia.getNome()) && "".equals(instancia.getCpf()));
    }
    
    @Test
    void testClienteMensage() {
        ClienteFrm instancia = new ClienteFrm();
        instancia.setMensagem("mensagem");
        assertEquals("mensagem", instancia.getMensagem());
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
