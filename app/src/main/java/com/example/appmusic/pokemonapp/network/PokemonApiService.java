package com.example.appmusic.pokemonapp.network;

import com.example.appmusic.pokemonapp.pojo.PokemonResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface PokemonApiService {

    @GET("pokemon/")
    Observable<PokemonResponse> getPokemonResponseApiService();
}
