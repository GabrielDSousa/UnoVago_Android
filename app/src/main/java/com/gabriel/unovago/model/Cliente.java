package com.gabriel.unovago.model;

import java.io.Serializable;

public class Cliente implements Serializable {
    private Long createdAt;
    private  Long updatedAt;
    private int id;
    private String nome;
    private String email;

    public Cliente() {
    }

    public Cliente(Long createdAt, Long updatedAt, int id, String nome, String email) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.id = id;
        this.nome = nome;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}