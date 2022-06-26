package com.example.appmusic.pokemonapp.ui.view_model;

import android.util.Log;

import com.example.appmusic.pokemonapp.pojo.Pokemon;
import com.example.appmusic.pokemonapp.pojo.PokemonResponse;
import com.example.appmusic.pokemonapp.repository.Repository;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PokemonViewModel extends ViewModel {

    MutableLiveData<ArrayList<Pokemon>> pokemonListMutableLiveData = new MutableLiveData<>();

    public Repository repository;

    public LiveData<List<Pokemon>> listLiveDataFav = null;

    public LiveData<List<Pokemon>> getListLiveDataFav() {
        return listLiveDataFav;
    }

    @ViewModelInject
    public PokemonViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<Pokemon>> getPokemonListMutableLiveData() {
        return pokemonListMutableLiveData;
    }

    public void getPokemons() {

        repository.getPokemonResponseRepository()
                .subscribeOn(Schedulers.io())
                .map(new Function<PokemonResponse, ArrayList<Pokemon>>() {
                    @Override
                    public ArrayList<Pokemon> apply(PokemonResponse pokemonResponse) throws Throwable {

                        ArrayList<Pokemon> pokemonsList = pokemonResponse.getResults();

                        int i = 0;

                        for (Pokemon pokemon : pokemonsList) {

                            String url = pokemon.getUrl();

                            String[] pokemonIndex = url.split("/");

                            DecimalFormat format = new DecimalFormat("000");

//                            pokemon.setUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/"
//                                    + pokemonIndex[pokemonIndex.length - 1] + ".png");


                            i++;

                            pokemon.setUrl("https://assets.pokemon.com/assets/cms2/img/pokedex/full/"
                                    + (format.format(i)) + ".png");

                        }

                        return pokemonsList;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> pokemonListMutableLiveData.setValue(result),
                        error -> Log.e("ViewModel", error.getMessage()));
    }

    public void insertPokemonVM(Pokemon pokemon) {

        repository.insertPokemonRepo(pokemon);
    }

    public void deletePokemonVM(String pokemonName) {

        repository.deletePokemonRepo(pokemonName);
    }

    public void getPokemonFav() {

        listLiveDataFav = repository.getPokemonLiveDataRepo();
    }
}
