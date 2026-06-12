package com.server.app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "activos")
@Getter
@Setter
public class Activo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String simbolo;
    private String mercado;
    private Double precioMercado;
    private String sector;
}
