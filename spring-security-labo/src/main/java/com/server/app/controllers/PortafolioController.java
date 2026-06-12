package com.server.app.controllers;

import com.server.app.dto.response.Pagination;
import com.server.app.dto.response.PaginationMeta;
import com.server.app.entities.Portafolio;
import com.server.app.entities.User;
import com.server.app.services.PortafolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/finanzas/portafolios")
@RequiredArgsConstructor
public class PortafolioController {

    private final PortafolioService service;

    @GetMapping
    public ResponseEntity<Pagination<Portafolio>> list(
            Authentication auth,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        User user = (User) auth.getPrincipal();

        var p = service.findAllByUser((long) user.getId(), page, size);

        return ResponseEntity.ok(new Pagination<>(
                p.getContent(),
                new PaginationMeta(
                        p.getNumber(),
                        p.getSize(),
                        p.getTotalPages(),
                        p.getTotalElements()
                )
        ));
    }

    @PostMapping
    public ResponseEntity<Portafolio> create(
            Authentication auth,
            @RequestBody Map<String, String> body
    ) {

        User user = (User) auth.getPrincipal();

        return ResponseEntity.ok(
                service.create(body.get("nombre"), body.get("riesgoPerfil"), user)
        );
    }
}
