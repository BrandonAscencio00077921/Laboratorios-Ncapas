package org.ncapas.hyrule.common.utils;


import jakarta.servlet.http.HttpServletRequest;
import org.ncapas.hyrule.domain.dto.response.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface ResponseBuilder {

    default <T> ResponseEntity<GeneralResponse<T>> buildResponse(
            String message,
            HttpStatus status,
            T data,
            HttpServletRequest request) {

        GeneralResponse<T> response = GeneralResponse.<T>builder()
                .message(message)
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .path(request.getRequestURI())
                .data(data)
                .build();

        return new ResponseEntity<>(response, status);
    }
}
