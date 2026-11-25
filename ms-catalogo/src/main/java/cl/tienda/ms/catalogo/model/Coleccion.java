package cl.tienda.ms.catalogo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "colecciones")
@Getter
@Setter
@NoArgsConstructor
public class Coleccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100, unique = true)
    private String nombre;
    @Column(length = 300)
    private String descripcion;
}
