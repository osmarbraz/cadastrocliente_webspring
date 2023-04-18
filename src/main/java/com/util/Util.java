package com.util;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * Métodos utilitários para manipular valores de objetos.
 *
 * @author osmar
 */
public class Util {

    /**
     * Construtor privado para impedir instãncias.
     */
    private Util() {

    }

    /**
     * Copia os valores de um objeto de origem para um objeto de destino.
     *
     * @param origem Objeto de origem.
     * @param destino Objeto de destino.
     */
    public static void nonNullCopyProperties(Object origem, Object destino) {
        BeanUtils.copyProperties(origem, destino, getNullPropertyNames(origem));
    }

    /**
     * Retorna nome de propriedades nulas.
     *
     * @param origem
     * @return Uma lista.
     */
    public static String[] getNullPropertyNames(Object origem) {
        final BeanWrapper origemBean = new BeanWrapperImpl(origem);
        java.beans.PropertyDescriptor[] descritorPropriedades = origemBean.getPropertyDescriptors();

        Set<String> nomesVazios = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : descritorPropriedades) {
            Object srcValue = origemBean.getPropertyValue(pd.getName());
            if (srcValue == null) {
                nomesVazios.add(pd.getName());
            }
        }
        String[] result = new String[nomesVazios.size()];
        return nomesVazios.toArray(result);
    }
}
