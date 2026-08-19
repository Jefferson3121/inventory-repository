package com.inventory.inventory_servic.dto.response;


import com.inventory.inventory_servic.domain.MovementReason;
import com.inventory.inventory_servic.domain.MovementType;

import java.time.LocalDateTime;

public record ResponseStockMovementDTO(
        long id,
        long batchId,
        String productName,
        MovementType type,
        MovementReason reason,
        long quantity,
        String notes,
        LocalDateTime movementDate
) {}