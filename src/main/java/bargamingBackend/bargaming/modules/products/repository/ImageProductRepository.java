package bargamingBackend.bargaming.modules.products.repository;

import bargamingBackend.bargaming.modules.products.model.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageProductRepository extends JpaRepository<ImageProduct, Long> {
    List<ImageProduct> findByProduct_ProductId(Long productId);
}
