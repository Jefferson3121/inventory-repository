package com.inventory.inventory_servic.dto.response;


import java.time.LocalDate;
import java.time.LocalDateTime;

public record ResponseBatchDTO(
        long id,
        String productName,
        String batchCode,
        LocalDate expirationDate,
        LocalDate manufacturingDate,
        long quantity,
        boolean expired,
        LocalDateTime createdAt
) {}
