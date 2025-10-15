package bargamingBackend.bargaming.modules.cart.repository;

import bargamingBackend.bargaming.modules.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByCliente_UserId(Long clienteId);

    Cart findByCliente_UserIdAndProduct_ProductId(Long clienteId, Long productId);
}
