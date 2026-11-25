package cl.tienda.ms.catalogo.repository;

import cl.tienda.ms.catalogo.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
