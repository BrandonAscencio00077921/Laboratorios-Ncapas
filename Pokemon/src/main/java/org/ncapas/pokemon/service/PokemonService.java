package org.ncapas.pokemon.service;

import org.ncapas.pokemon.model.Pokemon;
import org.ncapas.pokemon.repository.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> filtrarPorTipo(String tipo) {
        return pokemonRepository.findAll()
                .stream()
                .filter(p -> p.getTipo().equalsIgnoreCase(tipo))
                .toList();
    }

    public List<Pokemon> filtrarPorZona(String zona) {
        return pokemonRepository.findAll()
                .stream()
                .filter(p -> p.getZonas()
                        .stream()
                        .anyMatch(z -> z.equalsIgnoreCase(zona)))
                .toList();
    }

    public List<Pokemon> filtrarPorDebilidad(String debilidad) {
        return pokemonRepository.findAll()
                .stream()
                .filter(p -> p.getDebilidades()
                        .stream()
                        .anyMatch(d -> d.equalsIgnoreCase(debilidad)))
                .toList();
    }


}
