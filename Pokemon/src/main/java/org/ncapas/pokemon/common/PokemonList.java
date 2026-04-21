package org.ncapas.pokemon.common;

import org.ncapas.pokemon.model.Pokemon;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PokemonList {

    private final List<Pokemon> pokemons = List.of(
            new Pokemon(
                    "Charmander",
                    "Fuego",
                    List.of("Agua", "Roca", "Tierra"),
                    List.of("Hierba Alta", "Montaña"),
                    "Kanto"
            ),
            new Pokemon(
                    "Squirtle",
                    "Agua",
                    List.of("Planta", "Eléctrico"),
                    List.of("Lago", "Mar"),
                    "Kanto"
            ),
            new Pokemon(
                    "Pikachu",
                    "Eléctrico",
                    List.of("Tierra"),
                    List.of("Bosque", "Hierba Alta"),
                    "Kanto"
            )
    );

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

}
