package com.inventory.inventory_servic.dto.response;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        String message,
        LocalDateTime timestamp) {


    public ErrorResponseDTO(String message) {
            this(message, LocalDateTime.now());
        }
}
