package com.bjlngroup.soscamp;

public class Pagamentos {
    private int id;
    private int id_cliente;
    private float preco;

    public Pagamentos(int id_cliente, float preco) {
        this.id_cliente = id_cliente;
        this.preco = preco;
    }
}
