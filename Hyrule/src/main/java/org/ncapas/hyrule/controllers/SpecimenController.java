package org.ncapas.hyrule.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ncapas.hyrule.common.utils.ResponseBuilder;
import org.ncapas.hyrule.domain.dto.request.CreateSpecimenRequest;
import org.ncapas.hyrule.domain.dto.request.UpdateSpecimenRequest;
import org.ncapas.hyrule.domain.dto.response.GeneralResponse;
import org.ncapas.hyrule.domain.dto.response.PageableResponse;
import org.ncapas.hyrule.domain.dto.response.specimen.SpecimenResponse;
import org.ncapas.hyrule.services.SpecimenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/specimens")
@RequiredArgsConstructor
public class SpecimenController implements ResponseBuilder {

    private final SpecimenService service;

    @PostMapping
    public ResponseEntity<GeneralResponse<SpecimenResponse>> create(
            @Valid @RequestBody CreateSpecimenRequest request,
            HttpServletRequest http) {

        return buildResponse(
                "Specimen created",
                HttpStatus.CREATED,
                service.createSpecimen(request),
                http
        );
    }

    @GetMapping
    public ResponseEntity<GeneralResponse<PageableResponse<SpecimenResponse>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder,
            HttpServletRequest http) {

        return buildResponse(
                "Specimens retrieved",
                HttpStatus.OK,
                service.getAllSpecimens(page, size, sortBy, sortOrder),
                http
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse<SpecimenResponse>> getById(
            @PathVariable UUID id,
            HttpServletRequest http) {

        return buildResponse(
                "Specimen found",
                HttpStatus.OK,
                service.getSpecimenById(id),
                http
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse<SpecimenResponse>> update(
            @PathVariable UUID id,
            @RequestBody UpdateSpecimenRequest request,
            HttpServletRequest http) {

        return buildResponse(
                "Specimen updated",
                HttpStatus.OK,
                service.updateSpecimen(id, request),
                http
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse<SpecimenResponse>> delete(
            @PathVariable UUID id,
            HttpServletRequest http) {

        return buildResponse(
                "Specimen deleted",
                HttpStatus.OK,
                service.deleteSpecimen(id),
                http
        );
    }
}
