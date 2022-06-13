package com.dao;

import com.modelo.Cliente;

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
