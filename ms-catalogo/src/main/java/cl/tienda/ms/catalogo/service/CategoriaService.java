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
public class CategoriaService {
    private final CategoriaRepository repository;


    public List<Categoria> listar() {
        return repository.findAll();
    }

    public Categoria buscar(Long id) {
        return obtener(id);
    }

    @Transactional
    public Categoria crear(CategoriaRequest request) {
        Categoria entidad = new Categoria();
        cargarDatos(entidad, request);
        return repository.save(entidad);
    }

    @Transactional
    public Categoria actualizar(Long id, CategoriaRequest request) {
        Categoria entidad = obtener(id);
        cargarDatos(entidad, request);
        return repository.save(entidad);
    }

    @Transactional
    public void eliminar(Long id) {
        Categoria entidad = obtener(id);
        repository.delete(entidad);
        repository.flush();
    }


    private Categoria obtener(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrado/a"));
    }

    private void cargarDatos(Categoria entidad, CategoriaRequest request) {
        entidad.setNombre(request.nombre().trim());
    }

}
