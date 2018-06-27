package com.gabriel.unovago.envioDados;

import java.io.Serializable;

public class Pesquisar implements Serializable {
    private String origem;
    private String destino;
    private String dataSaida;
    private String dataChegada;

    public Pesquisar() {
    }

    public Pesquisar(String origem, String destino, String dataSaida, String dataChegada) {
        this.origem = origem;
        this.destino = destino;
        this.dataSaida = dataSaida;
        this.dataChegada = dataChegada;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
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
}

