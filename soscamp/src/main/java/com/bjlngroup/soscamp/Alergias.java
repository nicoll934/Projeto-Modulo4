package com.bjlngroup.soscamp;

public class Alergias {
    private int id;
    private int id_cliente;
    private String alergia;

    public Alergias(int id_cliente, String alergia) {
        this.id_cliente = id_cliente;
        this.alergia = alergia;
    }
}
