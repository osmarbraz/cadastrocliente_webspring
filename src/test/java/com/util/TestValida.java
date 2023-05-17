package com.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestValida {

    private Valida valida = null;

    /**
     * Instância uma classe de validação para ser utilizada nos testes.
     */
    @BeforeAll
    void inicializa() {
        valida = new Valida();
    }

    /**
     * Testa CPFs válidos.
     * 
     * Utiliza testes parametrizados.
     */
    @ParameterizedTest
    @ValueSource(strings = {"84807125206", "63883136395"})
    void testCPFValido(String cpf) {
                  
        boolean retorno = valida.validaCPF(cpf);
        
        assertTrue(retorno);
    }
    
    /**
     * Testa CPFs inválidos.
     * 
     * Utiliza testes parametrizados.
     */
    @ParameterizedTest
    @ValueSource(strings = {"11111111111",  //CPF todos os dígitos iguais
                            "123456",       //CPF sem os onze dígitos
                            "123456789012", //CPF com doze dígitos
                            " 12345678900", //CPF com espaço no início    
                            "12345678900 ", //CPF com espaço no fm
                            "12345678900",  //CPF com dígitos verificadores 0
                            "12345678901",  //CPF com dígitos verificadores 1                            
                            "12345678910",  //CPF com dígitos verificadores 10
                            "12345678999",  //CPF com dígitos verificadores 99
                            "12345678908",  //CPF inválido com o primeiro dígito verificador incorreto.
                            "12345678901",  //CPF inválido com o segundo dígito verificador incorreto.
                            "0065XAB22050", //CPF com problema de conversão
                            ""}             //CPF Vazio                            
                            )
    void testCPFInvalido(String cpf) {
                  
        boolean retorno = valida.validaCPF(cpf);
        
        assertFalse(retorno);
    }
    
    /**
     * Teste CPF com valor nulo.
     * 
     */
    @Test
    void testCPFNulo() {
                  
        boolean retorno = valida.validaCPF(null);
        
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
