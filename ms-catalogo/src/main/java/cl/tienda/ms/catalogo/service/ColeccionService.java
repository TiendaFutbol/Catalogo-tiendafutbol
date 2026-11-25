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
public class ColeccionService {
    private final ColeccionRepository repository;
    private final ProductoRepository productoRepository;

    public List<Coleccion> listar() {
        return repository.findAll();
    }

    public Coleccion buscar(Long id) {
        return obtener(id);
    }

    @Transactional
    public Coleccion crear(ColeccionRequest request) {
        Coleccion entidad = new Coleccion();
        cargarDatos(entidad, request);
        return repository.save(entidad);
    }

    @Transactional
    public Coleccion actualizar(Long id, ColeccionRequest request) {
        Coleccion entidad = obtener(id);
        cargarDatos(entidad, request);
        return repository.save(entidad);
    }

    @Transactional
    public void eliminar(Long id) {
        Coleccion entidad = obtener(id);
        if (productoRepository.existsByColecciones_Id(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La colección está asociada a productos");
        }
        repository.delete(entidad);
        repository.flush();
    }


    private Coleccion obtener(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Colección no encontrado/a"));
    }

    private void cargarDatos(Coleccion entidad, ColeccionRequest request) {
        entidad.setNombre(request.nombre().trim());
        entidad.setDescripcion(request.descripcion());
    }

}
