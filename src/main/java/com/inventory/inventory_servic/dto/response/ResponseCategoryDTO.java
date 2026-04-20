package com.inventory.inventory_servic.dto.response;

public record ResponseCatgoryDTO(int id, String name, String description, int IdCategoryParent, boolean active) {
}
