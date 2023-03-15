package com.bjlngroup.soscamp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "planos")
public class Plano {
    @Id
    @Column(name = "plano_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String descricao;
    private String localizacao;
    private int duracao;
    private float preco;

    public void atualizar(Plano plano) {
        this.nome = plano.getNome();
        this.descricao = plano.getDescricao();
        this.localizacao = plano.getLocalizacao();
        this.duracao = plano.getDuracao();
        this.preco = plano.getPreco();
    }
}
