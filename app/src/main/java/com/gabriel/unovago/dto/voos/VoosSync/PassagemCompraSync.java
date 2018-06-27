package com.gabriel.unovago.dto.voos.VoosSync;

import com.gabriel.unovago.dto.clientes.PassagensSync.VooSync;
import com.gabriel.unovago.model.Cliente;

public class PassagemCompraSync {
    private Long createdAt;
    private Long updatedAt;
    private int id;
    private Cliente cliente;
    private VooSync voo;

    public PassagemCompraSync() {
    }

    public PassagemCompraSync(Long createdAt, Long updatedAt, int id, Cliente cliente, VooSync voo) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.id = id;
        this.cliente = cliente;
        this.voo = voo;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public VooSync getVoo() {
        return voo;
    }

    public void setVoo(VooSync voo) {
        this.voo = voo;
    }
}
