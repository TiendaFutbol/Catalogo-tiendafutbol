package cl.tienda.ms.catalogo.dto;

import jakarta.validation.constraints.*;

public record ComunaRequest(
        @NotBlank @Size(max = 100) String nombre,
        @NotNull @Positive Long regionId
) {}
