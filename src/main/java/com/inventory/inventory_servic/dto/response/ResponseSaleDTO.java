package com.inventory.inventory_servic.dto.response;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ResponseSaleDTO(
        long id,
        LocalDateTime saleDate,
        BigDecimal amountCollected,
        BigDecimal expectedTotal,
        BigDecimal discrepancy,
        List<ResponseSaleDetailDTO> details
) {}
