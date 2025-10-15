package bargamingBackend.bargaming.modules.products.model;

import bargamingBackend.bargaming.common.enums.EstadoProducto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCTS")
@SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    private Long productId;

    private String nombre;
    private String marca;
    private String categoria;

    @Column(precision = 10)
    private Integer precio;

    @Column(name = "vendedor_id")
    private Long vendedorId;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EstadoProducto estado = EstadoProducto.DISPONIBLE;

    @Column(name = "recibe_ofertas")
    private Boolean recibeOfertas = false;

    // Relación 1:N con imágenes
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ImageProduct> images = new ArrayList<>();
}
