package com.danilorocha.nba.service;

import com.danilorocha.nba.entity.Jogo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JogoService {

    public static final String BASE_URL = "https://www.balldontlie.io/api/v1/games/";

    @GET("{id}")
    Call<Jogo> getJogo(@Path("id") int id);


}
