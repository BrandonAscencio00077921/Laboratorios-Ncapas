package org.ncapas.hyrule.services;

import org.ncapas.hyrule.domain.dto.request.CreateSpecimenRequest;
import org.ncapas.hyrule.domain.dto.request.UpdateSpecimenRequest;
import org.ncapas.hyrule.domain.dto.response.PageableResponse;
import org.ncapas.hyrule.domain.dto.response.specimen.SpecimenResponse;

import java.util.UUID;

public interface SpecimenService {

    SpecimenResponse createSpecimen(CreateSpecimenRequest request);

    PageableResponse<SpecimenResponse> getAllSpecimens(int page, int size, String sortBy, String sortDir);

    SpecimenResponse getSpecimenById(UUID id);

    SpecimenResponse updateSpecimen(UUID id, UpdateSpecimenRequest request);

    SpecimenResponse deleteSpecimen(UUID id);
}
