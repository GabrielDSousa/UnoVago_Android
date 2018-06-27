package com.gabriel.unovago.dto.voos.VoosSync;

import java.io.Serializable;

public class AeronaveSync implements Serializable{
    private Long createdAt;
    private Long updatedAt;
    private int id;
    private String nome;

    public AeronaveSync() {
    }

    public AeronaveSync(Long createdAt, Long updatedAt, int id, String nome) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.id = id;
        this.nome = nome;
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

