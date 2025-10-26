package bargamingBackend.bargaming.modules.products.repository;

import bargamingBackend.bargaming.modules.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByVendedor(User vendedor);
}
