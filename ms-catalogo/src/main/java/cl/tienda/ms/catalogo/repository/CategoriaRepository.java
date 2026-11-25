package cl.tienda.ms.catalogo.repository;

import cl.tienda.ms.catalogo.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
