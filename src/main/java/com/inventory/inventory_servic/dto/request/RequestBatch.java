package com.inventory.inventory_servic.dto.request;

import java.time.LocalDate;

public record RequestBatch(

        long productId,
        LocalDate expirationDate,
        LocalDate manufacturingDate
) {
}
