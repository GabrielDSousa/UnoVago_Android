package com.gabriel.unovago.retrofit;

import com.gabriel.unovago.services.ClienteService;
import com.gabriel.unovago.services.VooService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInializador {
    private final Retrofit retrofit;

    public RetrofitInializador() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);

        retrofit = new Retrofit.Builder().baseUrl("https://aeroporto-api.herokuapp.com/api/v1/")
                .addConverterFactory(JacksonConverterFactory.create()).client(client.build()).build();
    }

    public ClienteService getClienteService() {
        return retrofit.create(ClienteService.class);
    }

    public VooService getVooService() {
        return retrofit.create(VooService.class);
    }
}
