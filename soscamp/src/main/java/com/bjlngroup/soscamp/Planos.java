package com.bjlngroup.soscamp;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Table(name="Planos")
@Entity
public class Planos {
    @Id
    private int id;
    private String nome;
    private int duracao;

    @OneToMany
    @JoinColumn(name = "id_plano")
    private List<Clientes> campistas = new ArrayList<>();


    public Planos(String nome, int duracao) {
        this.nome = nome;
        this.duracao = duracao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
}
