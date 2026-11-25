package cl.tienda.ms.catalogo.service;

import cl.tienda.ms.catalogo.dto.*;
import cl.tienda.ms.catalogo.model.*;
import cl.tienda.ms.catalogo.repository.*;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductoService {
    private final ProductoRepository repository;
    private final CategoriaRepository categoriaRepository;
    private final ColeccionRepository coleccionRepository;

    public List<ProductoResponse> listar() {
        return repository.findAll(Sort.by("id")).stream().map(this::toResponse).toList();
    }

    public ProductoResponse buscar(Long id) {
        return toResponse(obtener(id));
    }

    @Transactional
    public ProductoResponse crear(ProductoRequest request) {
        Producto entidad = new Producto();
        cargarDatos(entidad, request);
        return toResponse(repository.save(entidad));
    }

    @Transactional
    public ProductoResponse actualizar(Long id, ProductoRequest request) {
        Producto entidad = obtener(id);
        cargarDatos(entidad, request);
        return toResponse(repository.save(entidad));
    }

    @Transactional
    public void eliminar(Long id) {
        Producto entidad = obtener(id);
        repository.delete(entidad);
        repository.flush();
    }

    public List<ProductoResponse> porCategoria(Long categoriaId) {
        return repository.findByCategoria_IdOrderByIdAsc(categoriaId).stream().map(this::toResponse).toList();
    }

    public List<ProductoResponse> porColeccion(Long coleccionId) {
        return repository.findDistinctByColecciones_IdOrderByIdAsc(coleccionId).stream().map(this::toResponse).toList();
    }

    public List<ProductoResponse> destacados() {
        return repository.findByEsPortadaTrueOrderByOrdenDestacadoAscIdAsc().stream().map(this::toResponse).toList();
    }

    public List<ProductoResponse> criticos(Integer maxStock) {
        if (maxStock < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El stock máximo no puede ser negativo");
        }
        return repository.findByStockLessThanEqualOrderByStockAscIdAsc(maxStock).stream().map(this::toResponse).toList();
    }

    private Producto obtener(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado/a"));
    }

    private void cargarDatos(Producto entidad, ProductoRequest request) {
        entidad.setTitulo(request.titulo().trim());
        entidad.setPrecio(request.precio());
        entidad.setImg(request.img());
        entidad.setDescripcion(request.descripcion());
        entidad.setCategoria(request.categoriaId() == null ? null : categoriaRepository.findById(request.categoriaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada")));
        entidad.setEsPortada(request.esPortada());
        entidad.setOrdenDestacado(request.ordenDestacado());
        entidad.setStock(request.stock());
        entidad.getColecciones().clear();
        if (request.coleccionIds() != null) {
            for (Long id : request.coleccionIds()) {
                entidad.getColecciones().add(coleccionRepository.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Colección no encontrada: " + id)));
            }
        }
    }

    private ProductoResponse toResponse(Producto entidad) {
        return new ProductoResponse(entidad.getId(), entidad.getTitulo(), entidad.getPrecio(), entidad.getImg(),
                entidad.getDescripcion(), entidad.getCategoria() == null ? null : entidad.getCategoria().getId(),
                entidad.isEsPortada(), entidad.getOrdenDestacado(), entidad.getStock(),
                entidad.getColecciones().stream().map(Coleccion::getId).sorted().toList());
    }
}
