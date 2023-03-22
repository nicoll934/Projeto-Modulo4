package com.bjlngroup.soscamp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "pagamentos")
public class Pagamento {
    @Id
    @Column(name = "id_pagamento")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Cliente cliente;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "id_plano")
    private Plano plano;

    private String data;
}
