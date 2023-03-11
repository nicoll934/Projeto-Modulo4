package com.bjlngroup.soscamp;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name= "Habilidades")
@Entity
public class Habilidades {
    @Id
    private int id;
    private int id_cliente;
    private String habilidades;

    public Habilidades(int id_cliente, String habilidades) {
        this.id_cliente = id_cliente;
        this.habilidades = habilidades;
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

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }
}
