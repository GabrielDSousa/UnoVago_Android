package com.gabriel.unovago.dto.voos.VoosSync;

import java.util.List;

public class CompraSync {
    private List<PassagemCompraSync> passagens;

    public CompraSync() {
    }

    public CompraSync(List<PassagemCompraSync> passagens) {
        this.passagens = passagens;
    }

    public List<PassagemCompraSync> getPassagens() {
        return passagens;
    }

    public void setPassagens(List<PassagemCompraSync> passagens) {
        this.passagens = passagens;
    }
}
