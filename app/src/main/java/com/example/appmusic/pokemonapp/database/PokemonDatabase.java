package com.example.appmusic.pokemonapp.database;

import com.example.appmusic.pokemonapp.dao.PokemonDao;
import com.example.appmusic.pokemonapp.pojo.Pokemon;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Pokemon.class, version = 1, exportSchema = false)
public abstract class PokemonDatabase extends RoomDatabase {

    public abstract PokemonDao pokemonDao();
}
