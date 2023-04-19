package com.dao;

import com.entidade.Cliente;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Data Acesse Objeto de Cliente.
 *
 * @author osmar
 */
@Repository
public interface ClienteDAO extends CrudRepository<Cliente, Integer> {

}
