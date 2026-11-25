package cl.tienda.ms.catalogo.dto;

import jakarta.validation.constraints.*;

public record RegionRequest(
        @NotBlank @Size(max = 100) String nombre
) {}
