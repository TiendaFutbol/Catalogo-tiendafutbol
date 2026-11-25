package cl.tienda.ms.catalogo.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;

public record ProductoRequest(
        @NotBlank @Size(max = 150) String titulo,
        @NotNull @DecimalMin("0.00") @Digits(integer = 8, fraction = 2) BigDecimal precio,
        @Size(max = 400) String img,
        @Size(max = 500) String descripcion,
        @Positive Long categoriaId,
        @NotNull Boolean esPortada,
        @PositiveOrZero Integer ordenDestacado,
        @NotNull @PositiveOrZero Integer stock,
        Set<@NotNull @Positive Long> coleccionIds
) {}
