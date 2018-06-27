package com.gabriel.unovago.dto.clientes.PassagensSync;

import java.io.Serializable;

public class VooSync implements Serializable{
    private Long createdAt;
    private Long updatedAt;
    private int id;
    private String dataSaida;
    private String dataChegada;
    private String destino;
    private String origem;
    private int capacidadeMaxima;
    private double valor;
    private int aeronave;

    public VooSync() {
    }

    public VooSync(Long createdAt, Long updatedAt, int id, String dataSaida, String dataChegada, String destino, String origem, int capacidadeMaxima, double valor, int aeronave) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.id = id;
        this.dataSaida = dataSaida;
        this.dataChegada = dataChegada;
        this.destino = destino;
        this.origem = origem;
        this.capacidadeMaxima = capacidadeMaxima;
        this.valor = valor;
        this.aeronave = aeronave;
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

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(String dataChegada) {
        this.dataChegada = dataChegada;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getAeronave() {
        return aeronave;
    }

    public void setAeronave(int aeronave) {
        this.aeronave = aeronave;
    }
}
