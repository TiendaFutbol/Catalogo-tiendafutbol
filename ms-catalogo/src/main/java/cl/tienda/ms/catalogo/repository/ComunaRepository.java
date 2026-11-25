package cl.tienda.ms.catalogo.repository;

import cl.tienda.ms.catalogo.model.Comuna;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ComunaRepository extends JpaRepository<Comuna, Long> {
    List<Comuna> findByRegion_IdOrderByIdAsc(Long regionId);
}
