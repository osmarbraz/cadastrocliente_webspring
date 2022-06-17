package com.formulario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class TestClienteFrm {

    /**
     * Testa o construtor sem argumentos do cliente.
     */
    @Test
    public void testCliente() {
        ClienteFrm instancia = new ClienteFrm();
        assertTrue((instancia.getClienteId()==0) && "".equals(instancia.getNome()) && "".equals(instancia.getCpf()));
    }

    @Test
    public void testClienteIdInt() {
        ClienteFrm instancia = new ClienteFrm();
        instancia.setClienteId(1);
        assertTrue((instancia.getClienteId()==1) && "".equals(instancia.getNome()) && "".equals(instancia.getCpf()));
    }
    
    @Test
    public void testClienteMensage() {
        ClienteFrm instancia = new ClienteFrm();
        instancia.setMensagem("mensagem");
        assertTrue("mensagem".equals(instancia.getMensagem()));
    }

    @Test
    public void testParaString() {
        ClienteFrm instancia = new ClienteFrm();
        String esperado = "clienteId:0 - Nome : - CPF :";
        assertEquals(esperado, instancia.toString());
    }   
}
