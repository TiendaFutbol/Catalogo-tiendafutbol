package cl.tienda.ms.catalogo.service;

import cl.tienda.ms.catalogo.dto.*;
import cl.tienda.ms.catalogo.model.*;
import cl.tienda.ms.catalogo.repository.*;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegionService {
    private final RegionRepository repository;


    public List<Region> listar() {
        return repository.findAll();
    }

    public Region buscar(Long id) {
        return obtener(id);
    }

    @Transactional
    public Region crear(RegionRequest request) {
        Region entidad = new Region();
        cargarDatos(entidad, request);
        return repository.save(entidad);
    }

    @Transactional
    public Region actualizar(Long id, RegionRequest request) {
        Region entidad = obtener(id);
        cargarDatos(entidad, request);
        return repository.save(entidad);
    }

    @Transactional
    public void eliminar(Long id) {
        Region entidad = obtener(id);
        repository.delete(entidad);
        repository.flush();
    }


    private Region obtener(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Región no encontrado/a"));
    }

    private void cargarDatos(Region entidad, RegionRequest request) {
        entidad.setNombre(request.nombre().trim());
    }

}
