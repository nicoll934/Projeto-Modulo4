package com.bjlngroup.soscamp.tables;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Pagamento {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Cliente cliente;
    private Plano plano;
    private String data;
}
