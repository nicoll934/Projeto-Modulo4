package com.bjlngroup.soscamp;

public class Remedios {
    private int id_cliente;
    private String nome;
    private int miligramas;
    private String horario;
    private int intervalo;
    private int quarto;

    public Remedios(int id_cliente, String nome, int miligramas, String horario, int intervalo, int quarto) {
        this.id_cliente = id_cliente;
        this.nome = nome;
        this.miligramas = miligramas;
        this.horario = horario;
        this.intervalo = intervalo;
        this.quarto = quarto;
    }
}
