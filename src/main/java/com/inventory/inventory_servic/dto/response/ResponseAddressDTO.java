package com.inventory.inventory_servic.dto.response;

public record ResponseAddressDTO(
        String street,
        String city,
        String state,
        String zipCode,
        String country
) {
}
