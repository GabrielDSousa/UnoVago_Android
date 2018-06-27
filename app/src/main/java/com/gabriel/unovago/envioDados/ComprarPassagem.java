package com.gabriel.unovago.envioDados;

import java.io.Serializable;

public class ComprarPassagem implements Serializable {
    private int idVoo;
    private  int idCliente;
    private int quantidade;
    private double pagamento;

    public ComprarPassagem() {
    }

    public ComprarPassagem(int idVoo, int idCliente, int quantidade, double pagamento) {
        this.idVoo = idVoo;
        this.idCliente = idCliente;
        this.quantidade = quantidade;
        this.pagamento = pagamento;
    }

    public int getIdVoo() {
        return idVoo;
    }

    public void setIdVoo(int idVoo) {
        this.idVoo = idVoo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPagamento() {
        return pagamento;
    }

    public void setPagamento(double pagamento) {
        this.pagamento = pagamento;
    }
}


