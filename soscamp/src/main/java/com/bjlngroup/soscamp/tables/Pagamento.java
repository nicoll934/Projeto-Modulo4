package com.bjlngroup.soscamp.tables;

import jakarta.persistence.*;

@Entity
@Table(name = "pagamentos")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    private Cliente cliente;
//    private Plano plano;
    private String data;

    public void atualizar(Pagamento pagamento) {
//        this.cliente = pagamento.getCliente();
//        this.plano = pagamento.getPlano();
        this.data = pagamento.getData();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

//    public Cliente getCliente() {
//        return cliente;
//    }
//
//    public void setCliente(Cliente cliente) {
//        this.cliente = cliente;
//    }
//
//    public Plano getPlano() {
//        return plano;
//    }
//
//    public void setPlano(Plano plano) {
//        this.plano = plano;
//    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
