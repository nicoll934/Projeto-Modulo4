package com.bjlngroup.soscamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="Remedios")
@Entity
public class Remedios {
    @Id
    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMiligramas() {
        return miligramas;
    }

    public void setMiligramas(int miligramas) {
        this.miligramas = miligramas;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }

    public int getQuarto() {
        return quarto;
    }

    public void setQuarto(int quarto) {
        this.quarto = quarto;
    }
}
