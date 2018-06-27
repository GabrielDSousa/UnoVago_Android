package com.gabriel.unovago.envioDados;

import java.io.Serializable;

public class ValidarCartao implements Serializable {
    private String cartao;

    public ValidarCartao() {
    }

    public ValidarCartao(String cartao) {
        this.cartao = cartao;
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }
}
