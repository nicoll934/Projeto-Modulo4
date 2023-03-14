package com.bjlngroup.soscamp.repositories;

import com.bjlngroup.soscamp.tables.Cliente;
import com.bjlngroup.soscamp.tables.Pagamento;
import org.springframework.data.repository.CrudRepository;

public interface PagamentosRepository extends CrudRepository<Pagamento, Long> {
}
