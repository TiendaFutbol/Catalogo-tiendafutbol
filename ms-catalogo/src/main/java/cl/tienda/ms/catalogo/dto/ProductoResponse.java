package cl.tienda.ms.catalogo.dto;

import java.math.BigDecimal;
import java.util.List;

public record ProductoResponse(
        Long id, String titulo, BigDecimal precio, String img, String descripcion,
        Long categoriaId, boolean esPortada, Integer ordenDestacado, Integer stock, List<Long> coleccionIds
) {}
