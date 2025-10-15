package bargamingBackend.bargaming.modules.products.repository;

import bargamingBackend.bargaming.modules.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoria(String categoria);

    List<Product> findByMarca(String marca);

    List<Product> findByNombreContainingIgnoreCase(String nombre);

    List<Product> findByVendedorId(Long vendedorId);
}
