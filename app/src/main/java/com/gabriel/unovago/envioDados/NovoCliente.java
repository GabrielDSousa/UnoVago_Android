package com.gabriel.unovago.envioDados;

import java.io.Serializable;

public class NovoCliente implements Serializable {
    private String nome;
    private String email;
    private String password;

    public NovoCliente() {
    }

    public NovoCliente(String nome, String email, String password) {
        this.nome = nome;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}