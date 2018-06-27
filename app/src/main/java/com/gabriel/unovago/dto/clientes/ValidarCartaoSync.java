package com.gabriel.unovago.dto.clientes;

import java.io.Serializable;

public class ValidarCartaoSync implements Serializable {
    private String isValid;

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public ValidarCartaoSync(String isValid) {

        this.isValid = isValid;
    }

    public ValidarCartaoSync() {

    }
}
