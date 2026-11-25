package cl.tienda.ms.catalogo.controller;

import cl.tienda.ms.catalogo.dto.*;
import cl.tienda.ms.catalogo.model.*;
import cl.tienda.ms.catalogo.service.OfertaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ofertas")
@RequiredArgsConstructor
@Tag(name = "Ofertas")
public class OfertaController {
    private final OfertaService service;

    @GetMapping
    @Operation(summary = "Listar ofertas")
    public List<OfertaResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID")
    public OfertaResponse buscar(@PathVariable("id") Long id) {
        return service.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear", security = @SecurityRequirement(name = "bearerAuth"))
    public OfertaResponse crear(@Valid @RequestBody OfertaRequest request) {
        return service.crear(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar", security = @SecurityRequirement(name = "bearerAuth"))
    public OfertaResponse actualizar(@PathVariable("id") Long id, @Valid @RequestBody OfertaRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar", security = @SecurityRequirement(name = "bearerAuth"))
    public void eliminar(@PathVariable("id") Long id) {
        service.eliminar(id);
    }

    @GetMapping("/producto/{productoId}")
    @Operation(summary = "porProducto")
    public List<OfertaResponse> porProducto(@PathVariable("productoId") Long productoId) {
        return service.porProducto(productoId);
    }

    @GetMapping("/vigentes")
    @Operation(summary = "vigentes")
    public List<OfertaResponse> vigentes() {
        return service.vigentes();
    }
}
