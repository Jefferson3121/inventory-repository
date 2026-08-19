package com.inventory.inventory_servic.dto.response;

import java.math.BigDecimal;

public record ResponseSaleDetailDTO(
        long id,
        String productName,
        String batchCode,
        long quantity,
        BigDecimal unitPrice
) {}