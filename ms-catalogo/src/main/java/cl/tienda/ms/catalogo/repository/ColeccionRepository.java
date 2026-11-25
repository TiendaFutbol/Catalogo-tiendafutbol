package cl.tienda.ms.catalogo.repository;

import cl.tienda.ms.catalogo.model.Coleccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColeccionRepository extends JpaRepository<Coleccion, Long> {
}
