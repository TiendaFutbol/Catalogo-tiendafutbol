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
public class ComunaService {
    private final ComunaRepository repository;
    private final RegionRepository regionRepository;

    public List<ComunaResponse> listar() {
        return repository.findAll(Sort.by("id")).stream().map(this::toResponse).toList();
    }

    public ComunaResponse buscar(Long id) {
        return toResponse(obtener(id));
    }

    @Transactional
    public ComunaResponse crear(ComunaRequest request) {
        Comuna entidad = new Comuna();
        cargarDatos(entidad, request);
        return toResponse(repository.save(entidad));
    }

    @Transactional
    public ComunaResponse actualizar(Long id, ComunaRequest request) {
        Comuna entidad = obtener(id);
        cargarDatos(entidad, request);
        return toResponse(repository.save(entidad));
    }

    @Transactional
    public void eliminar(Long id) {
        Comuna entidad = obtener(id);
        repository.delete(entidad);
        repository.flush();
    }

    public List<ComunaResponse> porRegion(Long regionId) {
        return repository.findByRegion_IdOrderByIdAsc(regionId).stream().map(this::toResponse).toList();
    }

    private Comuna obtener(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Comuna no encontrado/a"));
    }

    private void cargarDatos(Comuna entidad, ComunaRequest request) {
        entidad.setNombre(request.nombre().trim());
        entidad.setRegion(regionRepository.findById(request.regionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Región no encontrada")));
    }

    private ComunaResponse toResponse(Comuna entidad) {
        return new ComunaResponse(entidad.getId(), entidad.getNombre(), entidad.getRegion().getId());
    }
}
