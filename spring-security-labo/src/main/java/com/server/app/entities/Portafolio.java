package com.server.app.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "portafolios")
@Getter
@Setter
public class Portafolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private Double balanceTotal;

    private String riesgoPerfil;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;
}
