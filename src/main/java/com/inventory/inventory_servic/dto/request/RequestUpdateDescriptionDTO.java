package com.inventory.inventory_servic.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RequestUpdateDescriptionDTO(@NotBlank String description) {
}
