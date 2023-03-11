package com.bjlngroup.soscamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="Alergia")
@Entity
public class Alergias {
<<<<<<< HEAD
    private int id;
    private int id_cliente;
=======
    @Id
    private int id;

>>>>>>> 15b33feff58384d4bbea4ba1a0f4442cb197601d
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
