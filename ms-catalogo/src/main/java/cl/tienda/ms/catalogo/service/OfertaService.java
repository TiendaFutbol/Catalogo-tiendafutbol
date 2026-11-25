package cl.tienda.ms.catalogo.service;

import cl.tienda.ms.catalogo.dto.*;
import cl.tienda.ms.catalogo.model.*;
import cl.tienda.ms.catalogo.repository.*;
import java.util.List;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OfertaService {
    private final OfertaRepository repository;
    private final ProductoRepository productoRepository;

    public List<OfertaResponse> listar() {
        return repository.findAll(Sort.by("id")).stream().map(this::toResponse).toList();
    }

    public OfertaResponse buscar(Long id) {
        return toResponse(obtener(id));
    }

    @Transactional
    public OfertaResponse crear(OfertaRequest request) {
        Oferta entidad = new Oferta();
        cargarDatos(entidad, request);
        return toResponse(repository.save(entidad));
    }

    @Transactional
    public OfertaResponse actualizar(Long id, OfertaRequest request) {
        Oferta entidad = obtener(id);
        cargarDatos(entidad, request);
        return toResponse(repository.save(entidad));
    }

    @Transactional
    public void eliminar(Long id) {
        Oferta entidad = obtener(id);
        repository.delete(entidad);
        repository.flush();
    }

    public List<OfertaResponse> porProducto(Long productoId) {
        return repository.findByProducto_IdOrderByIdAsc(productoId).stream().map(this::toResponse).toList();
    }

    public List<OfertaResponse> vigentes() {
        return repository.buscarVigentes(OffsetDateTime.now()).stream().map(this::toResponse).toList();
    }

    private Oferta obtener(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Oferta no encontrado/a"));
    }

    private void cargarDatos(Oferta entidad, OfertaRequest request) {
        if (request.fechaFin() != null && !request.fechaFin().isAfter(request.fechaInicio())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha de fin debe ser posterior a la de inicio");
        }
        entidad.setProducto(productoRepository.findById(request.productoId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado")));
        entidad.setDescuentoPorcentaje(request.descuentoPorcentaje());
        entidad.setFechaInicio(request.fechaInicio());
        entidad.setFechaFin(request.fechaFin());
        entidad.setActiva(request.activa());
    }

    private OfertaResponse toResponse(Oferta entidad) {
        return new OfertaResponse(entidad.getId(), entidad.getProducto().getId(), entidad.getDescuentoPorcentaje(),
                entidad.getFechaInicio(), entidad.getFechaFin(), entidad.isActiva());
    }
}
