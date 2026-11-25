package cl.tienda.ms.catalogo.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record OfertaRequest(
        @NotNull @Positive Long productoId,
        @NotNull @DecimalMin("0.01") @DecimalMax("100.00") @Digits(integer = 3, fraction = 2) BigDecimal descuentoPorcentaje,
        @NotNull OffsetDateTime fechaInicio,
        OffsetDateTime fechaFin,
        @NotNull Boolean activa
) {}
