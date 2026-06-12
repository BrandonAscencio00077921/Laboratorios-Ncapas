package com.server.app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "inversiones")
@Getter
@Setter
public class Inversion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double cantidad;
    private Double precioCompra;

    private LocalDateTime fecha;

    private String estado; // ABIERTA / CERRADA

    @ManyToOne
    @JoinColumn(name = "portafolio_id")
    private Portafolio portafolio;

    @ManyToOne
    @JoinColumn(name = "activo_id")
    private Activo activo;
}