package com.server.app.services;

import com.server.app.entities.Activo;
import com.server.app.entities.Inversion;
import com.server.app.entities.Portafolio;
import com.server.app.repositories.ActivoRepository;
import com.server.app.repositories.InversionRepository;
import com.server.app.repositories.PortafolioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InversionService {

    private final InversionRepository inversionRepository;
    private final PortafolioRepository portafolioRepository;
    private final ActivoRepository activoRepository;

    @Transactional
    public Inversion crear(Long portafolioId, Long activoId, Double cantidad, Double precio) {

        Portafolio p = portafolioRepository.findById(portafolioId)
                .orElseThrow(() -> new RuntimeException("Portafolio no encontrado"));

        Activo a = activoRepository.findById(activoId)
                .orElseThrow(() -> new RuntimeException("Activo no encontrado"));

        Inversion inv = new Inversion();
        inv.setPortafolio(p);
        inv.setActivo(a);
        inv.setCantidad(cantidad);
        inv.setPrecioCompra(precio);
        inv.setFecha(LocalDateTime.now());
        inv.setEstado("ABIERTA");

        return inversionRepository.save(inv);
    }

    public Double calcularRendimiento(Long portafolioId) {

        List<Inversion> inversiones = inversionRepository.findByPortafolioId(portafolioId);

        double total = 0;

        for (Inversion i : inversiones) {
            double valorActual = i.getCantidad() * i.getActivo().getPrecioMercado();
            double valorCompra = i.getCantidad() * i.getPrecioCompra();

            total += (valorActual - valorCompra);
        }

        return total;
    }
}
