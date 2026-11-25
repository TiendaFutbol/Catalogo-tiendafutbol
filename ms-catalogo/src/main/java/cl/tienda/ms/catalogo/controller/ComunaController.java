package cl.tienda.ms.catalogo.controller;

import cl.tienda.ms.catalogo.dto.*;
import cl.tienda.ms.catalogo.model.*;
import cl.tienda.ms.catalogo.service.ComunaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comunas")
@RequiredArgsConstructor
@Tag(name = "Comunas")
public class ComunaController {
    private final ComunaService service;

    @GetMapping
    @Operation(summary = "Listar comunas")
    public List<ComunaResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID")
    public ComunaResponse buscar(@PathVariable("id") Long id) {
        return service.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear", security = @SecurityRequirement(name = "bearerAuth"))
    public ComunaResponse crear(@Valid @RequestBody ComunaRequest request) {
        return service.crear(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar", security = @SecurityRequirement(name = "bearerAuth"))
    public ComunaResponse actualizar(@PathVariable("id") Long id, @Valid @RequestBody ComunaRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar", security = @SecurityRequirement(name = "bearerAuth"))
    public void eliminar(@PathVariable("id") Long id) {
        service.eliminar(id);
    }

    @GetMapping("/region/{regionId}")
    @Operation(summary = "porRegion")
    public List<ComunaResponse> porRegion(@PathVariable("regionId") Long regionId) {
        return service.porRegion(regionId);
    }
}
