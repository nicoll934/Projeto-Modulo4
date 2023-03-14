package com.bjlngroup.soscamp.repositories;

import com.bjlngroup.soscamp.tables.Cliente;
import com.bjlngroup.soscamp.tables.Plano;
import org.springframework.data.repository.CrudRepository;

public interface PlanosRepository extends CrudRepository<Plano, Long> {
}
