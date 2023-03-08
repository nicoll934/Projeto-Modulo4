package com.bjlngroup.soscamp;

public class Clientes {
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
}
