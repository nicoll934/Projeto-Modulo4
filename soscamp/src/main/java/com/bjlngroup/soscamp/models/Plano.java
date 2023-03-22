package com.bjlngroup.soscamp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "planos")
public class Plano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotBlank
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
