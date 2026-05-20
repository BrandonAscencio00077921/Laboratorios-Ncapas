package org.ncapas.hyrule.domain.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Table(name = "specimens")
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Specimen {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String region;
    private Integer dangerLevel;
    private Boolean isFriendly;

}
