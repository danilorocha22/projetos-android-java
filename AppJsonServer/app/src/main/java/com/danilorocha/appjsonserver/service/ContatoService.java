package com.danilorocha.appjsonserver.service;

import com.danilorocha.appjsonserver.model.Contato;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ContatoService {
    //String BASE_URL = "https://localhost:3000/";
    String BASE_URL = "https://ed5b-2804-3690-8001-bd75-de09-bfc9-3859-42e6.ngrok.io/";

    @GET("contato")
    Call<List<Contato>> getContatos();
}
