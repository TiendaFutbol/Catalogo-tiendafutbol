package cl.tienda.ms.catalogo.dto;

import jakarta.validation.constraints.*;

public record ColeccionRequest(
        @NotBlank @Size(max = 100) String nombre,
        @Size(max = 300) String descripcion
) {}
