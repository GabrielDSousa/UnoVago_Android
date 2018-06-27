package com.gabriel.unovago.services;

import com.gabriel.unovago.dto.clientes.LoginSync;
import com.gabriel.unovago.dto.clientes.NovoClienteSync;
import com.gabriel.unovago.dto.clientes.PassagensSync.PassagensSync;
import com.gabriel.unovago.dto.clientes.ValidarCartaoSync;
import com.gabriel.unovago.dto.voos.VoosSync.CompraSync;
import com.gabriel.unovago.envioDados.ComprarPassagem;
import com.gabriel.unovago.envioDados.Login;
import com.gabriel.unovago.envioDados.NovoCliente;
import com.gabriel.unovago.envioDados.ValidarCartao;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ClienteService {

    @POST("clientes/login")
    Call<LoginSync> entrar(@Body Login login);

    @POST("clientes/validar-cartao")
    Call<ValidarCartaoSync> validarCartao(@Body ValidarCartao cartao);

    @GET("clientes/passagens/{id}")
    Call<PassagensSync> passagens(@Path("id") int id);

    @POST("clientes")
    Call<NovoClienteSync> novoCliente(@Body NovoCliente cliente);


//    @POST("clientes/login")
//    Call<LoginResposta> login(@Body LoginBody loginBody);


}
