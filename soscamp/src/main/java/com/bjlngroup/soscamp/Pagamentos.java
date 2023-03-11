package com.bjlngroup.soscamp;

import jakarta.persistence.*;

@Table(name="Pagamentos")
@Entity
public class Pagamentos {
    @Id
    private int id;

    @OneToOne
    @JoinColumn(name = "id_cliente")
    private Clientes cliente;
    private float preco;

    public Pagamentos(float preco) {
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

}
