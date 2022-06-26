package com.example.appmusic.pokemonapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import dagger.hilt.android.AndroidEntryPoint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.appmusic.pokemonapp.R;
import com.example.appmusic.pokemonapp.adapter.PokemonAdapter;
import com.example.appmusic.pokemonapp.pojo.Pokemon;
import com.example.appmusic.pokemonapp.ui.view_model.PokemonViewModel;

import java.util.ArrayList;
import java.util.List;

@AndroidEntryPoint
public class FavoriteActivity extends AppCompatActivity {

    private RecyclerView rv;
    private PokemonViewModel pokemonViewModel;
    private PokemonAdapter pokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        rv = findViewById(R.id.recyclerViewFav);
        pokemonAdapter = new PokemonAdapter(this);
        rv.setAdapter(pokemonAdapter);

        setupSwipe();

        Button btnFav = findViewById(R.id.btnToMain);

        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        pokemonViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);

        pokemonViewModel.getPokemonFav();

        pokemonViewModel.getListLiveDataFav().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {

                ArrayList<Pokemon> pokemonsList = new ArrayList<>();
                pokemonsList.addAll(pokemons);
                pokemonAdapter.setPokemonList(pokemonsList);
            }
        });
    }

    public void setupSwipe() {

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int swipePosition = viewHolder.getAdapterPosition();
                Pokemon pokemon = pokemonAdapter.getPokemonAtPosition(swipePosition);
                pokemonViewModel.deletePokemonVM(pokemon.getName());

                pokemonAdapter.notifyDataSetChanged();

                Toast.makeText( FavoriteActivity.this, "Pokemon has Deleted from Database.", Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rv);
    }
}