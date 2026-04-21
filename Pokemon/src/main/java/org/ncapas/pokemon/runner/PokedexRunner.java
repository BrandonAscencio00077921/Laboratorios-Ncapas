package org.ncapas.pokemon.runner;

import org.ncapas.pokemon.model.Pokemon;
import org.ncapas.pokemon.service.PokemonService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PokedexRunner implements CommandLineRunner {

    private final PokemonService pokemonService;

    public PokedexRunner(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @Override
    public void run(String... args) {

        imprimir("FILTRADOS POR TIPO ",
            pokemonService.filtrarPorTipo("Fuego"));

        imprimir("FILTRADOS POR ZONA",
            pokemonService.filtrarPorZona("Bosque"));

        imprimir("FILTRADOS POR DEBILIDAD",
            pokemonService.filtrarPorDebilidad("Tierra"));
    }


    private void imprimir(String titulo, List<Pokemon> lista) {
        System.out.println(titulo);
        lista.forEach(p ->
                System.out.println(
                        "[PKMN] Nombre: " + p.getNombre() +
                                " | Tipo: " + p.getTipo() +
                                " | Debilidades: " +
                                String.join(", ", p.getDebilidades())
                )
        );
    }


}
