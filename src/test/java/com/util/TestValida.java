package com.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestValida {

    private Valida valida = null;

    /**
     * Instância uma classe de validação.
     */
    @BeforeAll
    void inicializa() {
        valida = new Valida();
    }

    /**
     * Testa CPF válido.
     */
    @Test
    void testCPFValido1() {
        String cpfValido = "11111111111";
                  
        boolean retorno = valida.validaCPF(cpfValido);
        
        assertTrue(retorno);
    }

    /**
     * Testa CPF válido.
     */
    @Test
    void testCPFValido2() {
        String cpfValido = "84807125206";
                  
        boolean retorno = valida.validaCPF(cpfValido);
        
        assertTrue(retorno);
    }

    /**
     * Testa CPF válido.
     */
    @Test
    void testCPFValido3() {        
        String cpfValido = "63883136395";
                  
        boolean retorno = valida.validaCPF(cpfValido);
        
        assertTrue(retorno);
    }

    /**
     * Testa CPF inválido sem os 11 digítos.
     */
    @Test
    void testValidaCPFInvalidoSemOnzeDigitos() {
        String cpfValido = "123456";
                  
        boolean retorno = valida.validaCPF(cpfValido);
        
        assertFalse(retorno);
    }
    
    /**
     * Testa CPF inválido com o primeiro dígito verificador incorreto.
     */
    @Test
    void testValidaCPFInvalidoPrimeiroVerificadorIncorreto() {
        String cpfValido = "12345678908";
                  
        boolean retorno = valida.validaCPF(cpfValido);
        
        assertFalse(retorno);
    }
    
    /**
     * Testa CPF inválido com o segundo dígito verificador incorreto.
     */
    @Test
    void testValidaCPFInvalidoSegundoVerificadorIncorreto() {
        String cpfValido = "12345678901";
                  
        boolean retorno = valida.validaCPF(cpfValido);
        
        assertFalse(retorno);
    }

    /**
     * Testa CPF vazio.
     */
    @Test
    void testValidaCPFInvalidoVazio() {
        String cpfValido = "";
                  
        boolean retorno = valida.validaCPF(cpfValido);
        
        assertFalse(retorno);
    }
    
    /**
     * Testa CPF com problema na conversão.
     */
    @Test
    void testValidaCPFInvalidoProblemaConversao() {
        //CPF com problema na conversão
        String cpfValido = "0065XAB22050";
                  
        boolean retorno = valida.validaCPF(cpfValido);
        
        assertFalse(retorno);
    }

    /**
     * Finaliza a classe de validação.
     */
    @AfterAll
    void finaliza() {
        valida = null;
    }
}
