package com.bjlngroup.soscamp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "clientes")
public class Cliente {
    @Id
    @Column(name = "id_cliente")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    private String nome;
    private String email;
    private String senha;
    private String data_nasc;
    private String cpf;
    private String telefone;
    private String endereco;
    private String alergias;

    @OneToOne(cascade = CascadeType.ALL)
    private Plano plano;




}
