package com.server.app.controllers;

import com.server.app.entities.Inversion;
import com.server.app.services.InversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/finanzas")
@RequiredArgsConstructor
public class InversionController {

    private final InversionService service;

    @PostMapping("/inversiones")
    public ResponseEntity<Inversion> crear(@RequestBody Map<String, Object> body) {

        return ResponseEntity.ok(
                service.crear(
                        Long.valueOf(body.get("portafolioId").toString()),
                        Long.valueOf(body.get("activoId").toString()),
                        Double.valueOf(body.get("cantidad").toString()),
                        Double.valueOf(body.get("precio").toString())
                )
        );
    }

    @GetMapping("/portafolios/{id}/rendimiento")
    public ResponseEntity<?> rendimiento(@PathVariable Long id) {

        Double total = service.calcularRendimiento(id);

        return ResponseEntity.ok(Map.of("rendimiento", total));
    }
}
