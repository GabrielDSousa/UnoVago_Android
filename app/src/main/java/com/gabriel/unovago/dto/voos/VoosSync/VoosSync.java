package com.gabriel.unovago.dto.voos.VoosSync;

import java.io.Serializable;
import java.util.List;

public class VoosSync implements Serializable {
    List<VooSync> voos;

    public VoosSync() {
    }

    public VoosSync(List<VooSync> voos) {
        this.voos = voos;
    }

    public List<VooSync> getVoos() {
        return voos;
    }

    public void setVoos(List<VooSync> voos) {
        this.voos = voos;
    }
}
