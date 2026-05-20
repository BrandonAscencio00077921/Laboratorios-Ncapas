package org.ncapas.hyrule.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSpecimenRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String region;

    @NotNull
    private Integer dangerLevel;

    @NotNull
    private Boolean isFriendly;

}
