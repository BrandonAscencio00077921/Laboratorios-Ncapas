package com.server.app.controllers;

import com.server.app.entities.Activo;
import com.server.app.repositories.ActivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/finanzas/activos")
@RequiredArgsConstructor
public class ActivoController {

    private final ActivoRepository repository;

    @GetMapping
    public ResponseEntity<List<Activo>> list() {
        return ResponseEntity.ok(repository.findAll());
    }
}
