package cl.tienda.ms.catalogo.repository;

import cl.tienda.ms.catalogo.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoria_IdOrderByIdAsc(Long categoriaId);
    List<Producto> findByEsPortadaTrueOrderByOrdenDestacadoAscIdAsc();
    List<Producto> findByStockLessThanEqualOrderByStockAscIdAsc(Integer stock);
    List<Producto> findDistinctByColecciones_IdOrderByIdAsc(Long coleccionId);
    boolean existsByColecciones_Id(Long coleccionId);
}
