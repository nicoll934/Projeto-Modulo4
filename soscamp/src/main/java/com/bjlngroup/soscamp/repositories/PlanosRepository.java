package com.bjlngroup.soscamp.repositories;

import com.bjlngroup.soscamp.tables.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface PlanosRepository extends CrudRepository<Cliente, Long> {
}
