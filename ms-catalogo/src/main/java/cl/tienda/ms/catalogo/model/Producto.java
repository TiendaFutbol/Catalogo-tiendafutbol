package cl.tienda.ms.catalogo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Set;
import java.util.LinkedHashSet;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 150, unique = true)
    private String titulo;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;
    @Column(length = 400)
    private String img;
    @Column(length = 500)
    private String descripcion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
    @Column(name = "es_portada", nullable = false, columnDefinition = "NUMBER(1)")
    private boolean esPortada;
    @Column(name = "orden_destacado")
    private Integer ordenDestacado;
    @Column(nullable = false)
    private Integer stock = 0;
    @ManyToMany
    @JoinTable(name = "producto_coleccion",
            joinColumns = @JoinColumn(name = "producto_id"),
            inverseJoinColumns = @JoinColumn(name = "coleccion_id"))
    private Set<Coleccion> colecciones = new LinkedHashSet<>();
}
