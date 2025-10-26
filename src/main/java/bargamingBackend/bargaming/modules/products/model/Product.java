package bargamingBackend.bargaming.modules.products.model;

import bargamingBackend.bargaming.common.enums.EstadoProducto;
import bargamingBackend.bargaming.modules.auth.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private User vendedor;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EstadoProducto estado = EstadoProducto.DISPONIBLE;

    @Column(name = "recibe_ofertas")
    private Boolean recibeOfertas = false;

    @Column(name = "image_url", length = 1000)
    private String imageUrl;
}
