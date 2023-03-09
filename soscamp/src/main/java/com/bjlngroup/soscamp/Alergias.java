package com.bjlngroup.soscamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="Alergia")
@Entity
public class Alergias {
    @Id
    private int id;

    private String alergia;

    public Alergias(String alergia) {
        this.alergia = alergia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlergia() {
        return alergia;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }
}
