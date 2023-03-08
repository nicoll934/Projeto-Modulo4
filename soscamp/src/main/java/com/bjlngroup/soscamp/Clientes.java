package com.bjlngroup.soscamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Cliente")
public class Clientes {
    @Id
    private int id;
    private String nome;
    private int cpf;
    private int sus;
    private int telefone;
    private String endereco;
    private int quarto;

    public Clientes(String nome, int cpf, int sus, int telefone, String endereco, int quarto) {
        this.nome = nome;
        this.cpf = cpf;
        this.sus = sus;
        this.telefone = telefone;
        this.endereco = endereco;
        this.quarto = quarto;
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

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public int getSus() {
        return sus;
    }

    public void setSus(int sus) {
        this.sus = sus;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getQuarto() {
        return quarto;
    }

    public void setQuarto(int quarto) {
        this.quarto = quarto;
    }
}
