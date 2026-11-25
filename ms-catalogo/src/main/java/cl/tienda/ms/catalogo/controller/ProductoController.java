package cl.tienda.ms.catalogo.controller;

import cl.tienda.ms.catalogo.dto.*;
import cl.tienda.ms.catalogo.model.*;
import cl.tienda.ms.catalogo.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@Tag(name = "Productos")
public class ProductoController {
    private final ProductoService service;

    @GetMapping
    @Operation(summary = "Listar productos")
    public List<ProductoResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID")
    public ProductoResponse buscar(@PathVariable("id") Long id) {
        return service.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear", security = @SecurityRequirement(name = "bearerAuth"))
    public ProductoResponse crear(@Valid @RequestBody ProductoRequest request) {
        return service.crear(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar", security = @SecurityRequirement(name = "bearerAuth"))
    public ProductoResponse actualizar(@PathVariable("id") Long id, @Valid @RequestBody ProductoRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar", security = @SecurityRequirement(name = "bearerAuth"))
    public void eliminar(@PathVariable("id") Long id) {
        service.eliminar(id);
    }

    @GetMapping("/categoria/{categoriaId}")
    @Operation(summary = "porCategoria")
    public List<ProductoResponse> porCategoria(@PathVariable("categoriaId") Long categoriaId) {
        return service.porCategoria(categoriaId);
    }

    @GetMapping("/coleccion/{coleccionId}")
    @Operation(summary = "porColeccion")
    public List<ProductoResponse> porColeccion(@PathVariable("coleccionId") Long coleccionId) {
        return service.porColeccion(coleccionId);
    }

    @GetMapping("/destacados")
    @Operation(summary = "destacados")
    public List<ProductoResponse> destacados() {
        return service.destacados();
    }

    @GetMapping("/criticos")
    @Operation(summary = "criticos", security = @SecurityRequirement(name = "bearerAuth"))
    public List<ProductoResponse> criticos(@RequestParam(name = "maxStock", defaultValue = "5") Integer maxStock) {
        return service.criticos(maxStock);
    }
}
