package org.ncapas.hyrule.domain.dto.request;

import lombok.Data;

@Data
public class UpdateSpecimenRequest {
    private String name;
    private String region;
    private Integer dangerLevel;
    private Boolean isFriendly;

}
