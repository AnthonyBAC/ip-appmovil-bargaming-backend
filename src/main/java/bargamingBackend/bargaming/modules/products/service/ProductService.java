package bargamingBackend.bargaming.modules.products.service;

import bargamingBackend.bargaming.modules.products.model.Product;
import bargamingBackend.bargaming.modules.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getFilteredProducts(String categoria, String marca, String nombre) {
        if (categoria != null)
            return productRepository.findByCategoria(categoria);
        if (marca != null)
            return productRepository.findByMarca(marca);
        if (nombre != null)
            return productRepository.findByNombreContainingIgnoreCase(nombre);
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updated) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        existing.setNombre(updated.getNombre());
        existing.setMarca(updated.getMarca());
        existing.setCategoria(updated.getCategoria());
        existing.setPrecio(updated.getPrecio());
        existing.setRecibeOfertas(updated.getRecibeOfertas());
        existing.setEstado(updated.getEstado());

        return productRepository.save(existing);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
