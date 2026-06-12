package com.server.app.services;

import com.server.app.entities.Portafolio;
import com.server.app.entities.User;
import com.server.app.repositories.PortafolioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortafolioService {

    private final PortafolioRepository repository;

    public Page<Portafolio> findAllByUser(Long userId, int page, int size) {
        return repository.findByUsuarioId(userId, PageRequest.of(page, size));
    }

    @Transactional
    public Portafolio create(String nombre, String riesgo, User user) {

        Portafolio p = new Portafolio();
        p.setNombre(nombre);
        p.setRiesgoPerfil(riesgo);
        p.setBalanceTotal(0.0);
        p.setUsuario(user);

        return repository.save(p);
    }

    public Portafolio findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Portafolio no encontrado"));
    }
}

