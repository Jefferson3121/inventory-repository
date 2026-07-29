package com.inventory.inventory_servic.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record ResponseSupplierDTO(
        long id,
        String name,
        String contactName,
        String phone,
        String email,
        ResponseAddressDTO address,
        boolean active,
        List<ResponseProductSummaryDTO> products,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
