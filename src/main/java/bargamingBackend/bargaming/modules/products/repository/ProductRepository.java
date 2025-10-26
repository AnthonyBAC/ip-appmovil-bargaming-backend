package bargamingBackend.bargaming.modules.products.repository;

import bargamingBackend.bargaming.modules.products.model.Product;
import bargamingBackend.bargaming.modules.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByVendedor(User vendedor);
}
