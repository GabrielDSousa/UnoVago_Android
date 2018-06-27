package com.gabriel.unovago.dto.clientes;

import com.gabriel.unovago.model.Cliente;

import java.io.Serializable;

public class NovoClienteSync implements Serializable {
    private Cliente cliente;

    public NovoClienteSync() {
    }

    public NovoClienteSync(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
