package cl.tienda.ms.catalogo.controller;

import cl.tienda.ms.catalogo.dto.*;
import cl.tienda.ms.catalogo.model.*;
import cl.tienda.ms.catalogo.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
@Tag(name = "Categorias")
public class CategoriaController {
    private final CategoriaService service;

    @GetMapping
    @Operation(summary = "Listar categorias")
    public List<Categoria> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID")
    public Categoria buscar(@PathVariable("id") Long id) {
        return service.buscar(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear", security = @SecurityRequirement(name = "bearerAuth"))
    public Categoria crear(@Valid @RequestBody CategoriaRequest request) {
        return service.crear(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar", security = @SecurityRequirement(name = "bearerAuth"))
    public Categoria actualizar(@PathVariable("id") Long id, @Valid @RequestBody CategoriaRequest request) {
        return service.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Eliminar", security = @SecurityRequirement(name = "bearerAuth"))
    public void eliminar(@PathVariable("id") Long id) {
        service.eliminar(id);
    }
}
