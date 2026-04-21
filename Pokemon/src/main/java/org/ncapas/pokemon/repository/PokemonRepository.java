package org.ncapas.pokemon.repository;

import org.ncapas.pokemon.common.PokemonList;
import org.ncapas.pokemon.model.Pokemon;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PokemonRepository {

    private final PokemonList pokemonList;
    public PokemonRepository(PokemonList pokemonList) {
        this.pokemonList = pokemonList;
    }

    public List<Pokemon> findAll() {
        return pokemonList.getPokemons();
    }

}
