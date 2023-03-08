package com.bjlngroup.soscamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="Alergia")
@Entity
public class Alergias {
    @Id
    private int id;
    private int id_cliente;
    private String alergia;

    public Alergias(int id_cliente, String alergia) {
        this.id_cliente = id_cliente;
        this.alergia = alergia;
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

    public String getAlergia() {
        return alergia;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }
}
