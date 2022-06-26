package com.example.appmusic.pokemonapp.di;

import android.app.Application;

import com.example.appmusic.pokemonapp.dao.PokemonDao;
import com.example.appmusic.pokemonapp.database.PokemonDatabase;

import javax.inject.Singleton;

import androidx.room.Room;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DatabaseModule {

    @Provides
    @Singleton
    public static PokemonDatabase pokemonDatabase(Application application) {

        return Room.databaseBuilder(application, PokemonDatabase.class, "pokemon_fav")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static PokemonDao pokemonDao(PokemonDatabase pokemonDatabase) {

        return pokemonDatabase.pokemonDao();
    }
}
