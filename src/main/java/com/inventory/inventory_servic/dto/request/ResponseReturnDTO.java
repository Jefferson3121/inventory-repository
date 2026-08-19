package com.inventory.inventory_servic.dto.request;

import com.inventory.inventory_servic.domain.ReturnReason;

import java.time.LocalDateTime;

public record ResponseReturnDTO(
        long id,
        long saleDetailId,
        String productName,
        long quantity,
        ReturnReason reason,
        String notes,
        LocalDateTime returnDate
) {}
