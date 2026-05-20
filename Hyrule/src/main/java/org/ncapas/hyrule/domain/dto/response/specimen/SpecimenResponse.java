package org.ncapas.hyrule.domain.dto.response.specimen;


import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class SpecimenResponse {
    private UUID id;
    private String name;
    private String region;
    private Integer dangerLevel;
    private Boolean isFriendly;
}
