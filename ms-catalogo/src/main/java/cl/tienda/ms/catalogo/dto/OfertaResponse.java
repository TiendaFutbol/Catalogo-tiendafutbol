package cl.tienda.ms.catalogo.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record OfertaResponse(
        Long id, Long productoId, BigDecimal descuentoPorcentaje, OffsetDateTime fechaInicio,
        OffsetDateTime fechaFin, boolean activa
) {}
