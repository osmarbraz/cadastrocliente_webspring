package com.util;

/**
 * Classe utilitária para conter operações de validação
 *
 * @author osmarbraz
 */
public class Valida {

     /**
     * Valida os digitos verificadores de um CPF.
     *
     * @param cpf Um literal com um cpf de cliente.
     * @return Se o cpf é válido.
     */
     public boolean validaCPF(String cpf) {        
        //Verifica o tamanho do cpf
        if (cpf == null || cpf.length() != 11 || cpf.matches(cpf.charAt(0) + "{11}")) {
            return false;
        }
        
        int[] numeros = new int[11];
        for (int i = 0; i < 11; i++) {
            numeros[i] = Character.getNumericValue(cpf.charAt(i));
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += numeros[i] * (10 - i);
        }
        //Avalia o primeiro digito verificador
        int restoDivisao = soma % 11;
        int primeiroDigitoVerificador = restoDivisao < 2 ? 0 : 11 - restoDivisao;

        if (numeros[9] != primeiroDigitoVerificador) {
            return false;
        }

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += numeros[i] * (11 - i);
        }

        //Avalia o segundo digito verificador
        restoDivisao = soma % 11;
        int segundoDigitoVerificador = restoDivisao < 2 ? 0 : 11 - restoDivisao;

        return numeros[10] == segundoDigitoVerificador;
    }
}
