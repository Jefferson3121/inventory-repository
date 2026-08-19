package com.inventory.inventory_servic.dto.response;

import java.math.BigDecimal;

public record ResponsePurchaseDetailDTO(
        long id,
        String productName,
        long quantity,
        BigDecimal unitPrice,
        String batchCode
) {
}
