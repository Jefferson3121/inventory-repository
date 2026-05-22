package com.inventory.inventory_servic.application.dto.response;

public record ResponseCategoryDTO(int id, String name, String description, int idCategoryParent, boolean active) {
}
