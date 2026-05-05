package org.ncapas.jujustsuapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="sorcerers")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sorcerer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JsonIgnore
    private UUID id;

    @Column
    public String name;
    @Column
    public String grade;
    @Column
    public String cursedTechnique;
    @Column
    public String school;
    @Column
    public Double cursedEnergy;
    @Column
    public Boolean isActive;

}
