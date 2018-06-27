package com.gabriel.unovago.dto.clientes.PassagensSync;

import java.io.Serializable;

public class PassagemSync implements Serializable{
    private Long createdAt;
    private Long updatedAt;
    private int id;
    private int cliente;
    private VooSync voo;

    public PassagemSync() {
    }

    public PassagemSync(Long createdAt, Long updatedAt, int id, int cliente, VooSync voo) {
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

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public VooSync getVoo() {
        return voo;
    }

    public void setVoo(VooSync voo) {
        this.voo = voo;
    }

    @Override
    public String toString() {
        return "Data Chegada: "+getVoo().getDataChegada() + "\n"+
                "Data Saida: "+getVoo().getDataSaida() + "\n"+
                "Origem: "+getVoo().getOrigem() + "\n"+
                "Destino: "+getVoo().getDestino() + "\n";
    }
}
