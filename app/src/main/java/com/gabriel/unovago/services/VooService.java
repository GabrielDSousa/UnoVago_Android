package com.gabriel.unovago.services;

import com.gabriel.unovago.dto.clientes.PassagensSync.PassagensSync;
import com.gabriel.unovago.dto.voos.VoosSync.CompraSync;
import com.gabriel.unovago.dto.voos.VoosSync.VoosSync;
import com.gabriel.unovago.envioDados.ComprarPassagem;
import com.gabriel.unovago.envioDados.Pesquisar;
import com.gabriel.unovago.dto.clientes.PassagensSync.PassagemSync;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface VooService {
    @GET("voos")
    Call<VoosSync> voos();

    @POST("voos/pesquisar")
    Call<VoosSync> pesquisar(@Body Pesquisar pesquisa);

    @POST("voos/comprar-passagem")
    Call<CompraSync> comprarPassagem(@Body ComprarPassagem compra);
}
