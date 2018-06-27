package com.gabriel.unovago.envioDados;

import java.io.Serializable;

public class CancelarPassagem implements Serializable {
    private int idPassagem;

    public CancelarPassagem() {
    }

    public CancelarPassagem(int idPassagem) {
        this.idPassagem = idPassagem;
    }

    public int getIdPassagem() {
        return idPassagem;
    }

    public void setIdPassagem(int idPassagem) {
        this.idPassagem = idPassagem;
    }
}
