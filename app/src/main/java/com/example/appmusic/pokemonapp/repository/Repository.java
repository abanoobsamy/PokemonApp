package com.example.appmusic.pokemonapp.repository;

import com.example.appmusic.pokemonapp.dao.PokemonDao;
import com.example.appmusic.pokemonapp.network.PokemonApiService;
import com.example.appmusic.pokemonapp.pojo.Pokemon;
import com.example.appmusic.pokemonapp.pojo.PokemonResponse;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import io.reactivex.rxjava3.core.Observable;

public class Repository {

    public PokemonApiService pokemonApiService;

    public PokemonDao pokemonDao;

    @Inject
    public Repository(PokemonApiService pokemonApiService, PokemonDao pokemonDao) {
        this.pokemonApiService = pokemonApiService;
        this.pokemonDao = pokemonDao;
    }

    public Observable<PokemonResponse> getPokemonResponseRepository() {

        return pokemonApiService.getPokemonResponseApiService();
    }

    public void insertPokemonRepo(Pokemon pokemon) {

        pokemonDao.insertPokemon(pokemon);
    }

    public void deletePokemonRepo(String pokemonName) {

        pokemonDao.deletePokemon(pokemonName);
    }

    public LiveData<List<Pokemon>> getPokemonLiveDataRepo() {

        return pokemonDao.getPokemonLiveData();
    }

}
