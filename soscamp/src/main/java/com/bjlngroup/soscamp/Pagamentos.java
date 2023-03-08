package com.bjlngroup.soscamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="Pagamentos")
@Entity
public class Pagamentos {
    @Id
    private int id;
    private int id_cliente;
    private float preco;

    public Pagamentos(int id_cliente, float preco) {
        this.id_cliente = id_cliente;
        this.preco = preco;
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

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
}
