package com.example.appmusic.pokemonapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appmusic.pokemonapp.R;
import com.example.appmusic.pokemonapp.pojo.Pokemon;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private ArrayList<Pokemon> pokemonsList = new ArrayList<>();

    private Context mContext;

    public PokemonAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setPokemonList(ArrayList<Pokemon> pokemonsList) {
        this.pokemonsList = pokemonsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new PokemonViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pokemon, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {

        Pokemon pokemon = pokemonsList.get(position);

        holder.tvName.setText(pokemon.getName());

        Glide.with(mContext).load(pokemon.getUrl())
                .error(R.drawable.clean).into(holder.ivPokemon);
    }

    @Override
    public int getItemCount() {
        return pokemonsList.size();
    }

    public Pokemon getPokemonAtPosition(int position) {

        return pokemonsList.get(position);
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        ImageView ivPokemon;

        public PokemonViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvNamePokemonItem);
            ivPokemon = itemView.findViewById(R.id.ivPokemonItem);
        }
    }
}
