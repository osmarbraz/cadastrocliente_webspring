package com.util;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.entidade.Cliente;
import static com.util.Util.nonNullCopyProperties;

class TestUtil {

    @Test
    void testnonNullCopyProperties() {
        Cliente clienteOrigem = new Cliente(1, "Joao", "11111111111");
        Cliente clienteDestino = new Cliente(2, "Maria", "22222222222");

        nonNullCopyProperties(clienteOrigem, clienteDestino);

        assertTrue(
                (clienteOrigem.getClienteId().equals(clienteDestino.getClienteId()))
                && (clienteOrigem.getNome().equals(clienteDestino.getNome()))
                && (clienteOrigem.getCpf().equals(clienteDestino.getCpf())));
    }
    
      @Test
    void testnonNullCopyPropertiesNomesVazios() {
        Cliente clienteOrigem = new Cliente(1, null, "11111111111");
        Cliente clienteDestino = new Cliente(2, "Maria", "22222222222");

        nonNullCopyProperties(clienteOrigem, clienteDestino);

        assertTrue(
                (clienteOrigem.getClienteId().equals(clienteDestino.getClienteId()))
                && ("Maria".equals(clienteDestino.getNome()))
                && (clienteOrigem.getCpf().equals(clienteDestino.getCpf())));
    }
}
