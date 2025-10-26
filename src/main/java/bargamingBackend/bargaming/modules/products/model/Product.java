package bargamingBackend.bargaming.modules.products.model;

import bargamingBackend.bargaming.modules.auth.model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUCTS")
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    private Long productId;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private Integer precio;

    private Boolean recibeOfertas;

    @Column(length = 500)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(role = VENDEDOR, referencedColumnName = "user_id")
    private User vendedor;

    
}
