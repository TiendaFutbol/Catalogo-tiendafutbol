package cl.tienda.ms.catalogo.repository;

import cl.tienda.ms.catalogo.model.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.OffsetDateTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OfertaRepository extends JpaRepository<Oferta, Long> {
    List<Oferta> findByProducto_IdOrderByIdAsc(Long productoId);
    @Query("select o from Oferta o where o.activa = true and o.fechaInicio <= :ahora and (o.fechaFin is null or o.fechaFin > :ahora) order by o.id")
    List<Oferta> buscarVigentes(@Param("ahora") OffsetDateTime ahora);
}
