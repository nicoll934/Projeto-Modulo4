package com.bjlngroup.soscamp.request;

public class PagamentoRequest {
    private long cliente_id;
    private long plano_id;

    public long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(long cliente_id) {
        this.cliente_id = cliente_id;
    }

    public long getPlano_id() {
        return plano_id;
    }

    public void setPlano_id(long plano_id) {
        this.plano_id = plano_id;
    }
}
