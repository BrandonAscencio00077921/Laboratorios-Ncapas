package org.ncapas.hyrule.services.impl;

import lombok.RequiredArgsConstructor;
import org.ncapas.hyrule.common.mappers.SpecimenMapper;
import org.ncapas.hyrule.domain.dto.request.CreateSpecimenRequest;
import org.ncapas.hyrule.domain.dto.request.UpdateSpecimenRequest;
import org.ncapas.hyrule.domain.dto.response.PageableResponse;
import org.ncapas.hyrule.domain.dto.response.specimen.SpecimenResponse;
import org.ncapas.hyrule.domain.entities.Specimen;
import org.ncapas.hyrule.exceptions.ResourceNotFoundException;
import org.ncapas.hyrule.repositories.SpecimenRepository;
import org.ncapas.hyrule.services.SpecimenService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpecimenServiceImpl implements SpecimenService {

    private final SpecimenRepository repository;
    private final SpecimenMapper mapper;

    @Override
    public SpecimenResponse createSpecimen(CreateSpecimenRequest request) {
        return mapper.toDto(repository.save(mapper.toEntity(request)));
    }

    @Override
    public PageableResponse<SpecimenResponse> getAllSpecimens(int page, int size, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        PageRequest pageable = PageRequest.of(page, size, sort);

        Page<Specimen> result = repository.findAll(pageable);

        if (result.isEmpty()) {
            throw new ResourceNotFoundException("No specimens found");
        }

        Page<SpecimenResponse> dtoPage = mapper.toDtoPage(result);

        return PageableResponse.<SpecimenResponse>builder()
                .content(dtoPage.getContent())
                .page(dtoPage.getNumber())
                .size(dtoPage.getSize())
                .totalElements(dtoPage.getTotalElements())
                .totalPages(dtoPage.getTotalPages())
                .last(dtoPage.isLast())
                .build();
    }

    @Override
    public SpecimenResponse getSpecimenById(UUID id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specimen not found")));
    }

    @Override
    public SpecimenResponse updateSpecimen(UUID id, UpdateSpecimenRequest request) {
        getSpecimenById(id);
        return mapper.toDto(repository.save(mapper.toEntityUpdate(request, id)));
    }

    @Override
    public SpecimenResponse deleteSpecimen(UUID id) {
        SpecimenResponse specimen = getSpecimenById(id);
        repository.deleteById(id);
        return specimen;
    }
}
