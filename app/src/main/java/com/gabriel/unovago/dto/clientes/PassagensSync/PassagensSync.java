package com.gabriel.unovago.dto.clientes.PassagensSync;

import java.io.Serializable;
import java.util.List;

public class PassagensSync implements Serializable {
    List<PassagemSync> passagens;

    public PassagensSync() {
    }

    public PassagensSync(List<PassagemSync> passagens) {
        this.passagens = passagens;
    }

    public List<PassagemSync> getPassagens() {
        return passagens;
    }

    public void setPassagens(List<PassagemSync> passagens) {
        this.passagens = passagens;
    }
}
