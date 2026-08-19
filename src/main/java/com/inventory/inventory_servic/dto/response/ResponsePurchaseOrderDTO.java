package com.inventory.inventory_servic.dto.response;

import com.inventory.inventory_servic.domain.PurchaseOrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ResponsePurchaseOrderDTO(
        long id,
        String supplierName,
        LocalDate orderDate,
        PurchaseOrderStatus status,
        List<ResponsePurchaseDetailDTO> details,
        LocalDateTime createdAt
) {}