package bargamingBackend.bargaming.modules.cart.model;

import bargamingBackend.bargaming.modules.auth.model.User;
import bargamingBackend.bargaming.modules.products.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CART")
@SequenceGenerator(name = "cart_seq", sequenceName = "cart_seq", allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
    private Long cartId;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private User cliente;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Product product;

    private Integer cantidad;

    private Integer subtotal;
}
