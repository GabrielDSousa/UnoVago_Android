package com.gabriel.unovago.utils;

public abstract class formataData {

    public String formata(String dataErrada){
        String[] separated = dataErrada.split("T");
        String d0 = separated[0]; // this will contain "2016-10-02"
        String[] separated2 = d0.split("-");
        String dAno = separated2[0];
        String dMes = separated2[1];
        String dDia = separated2[2];
        String dataCorreta = dDia+"/"+dMes+"/"+dAno;

        return dataCorreta;
    }
}
