package cl.tienda.ms.catalogo.dto;

import jakarta.validation.constraints.*;

public record CategoriaRequest(
        @NotBlank @Size(max = 50) String nombre
) {}
