package bargamingBackend.bargaming.modules.products.service;

import bargamingBackend.bargaming.common.enums.Role;
import bargamingBackend.bargaming.modules.auth.model.User;
import bargamingBackend.bargaming.modules.auth.repository.UserRepository;
import bargamingBackend.bargaming.modules.products.model.Product;
import bargamingBackend.bargaming.modules.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public Product createProductWithImage(MultipartFile file, String nombre, String marca,
            String categoria, Integer precio,
            Boolean recibeOfertas, Principal principal)
            throws IOException {

        String imageUrl = cloudinaryService.uploadImage(file);

        User vendedor = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));

        if (vendedor.getRole() != Role.VENDEDOR && vendedor.getRole() != Role.ADMIN) {
            throw new RuntimeException("El usuario no tiene permisos para publicar productos");
        }

        Product product = new Product();
        product.setNombre(nombre);
        product.setMarca(marca);
        product.setCategoria(categoria);
        product.setPrecio(precio);
        product.setRecibeOfertas(recibeOfertas);
        product.setImageUrl(imageUrl);
        product.setVendedor(vendedor);

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsBySeller(Long userId) {
        User vendedor = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return productRepository.findByVendedor(vendedor);
    }

    public Product createProduct(Product product, Principal principal) {
        User vendedor = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));

        if (vendedor.getRole() != Role.VENDEDOR && vendedor.getRole() != Role.ADMIN) {
            throw new RuntimeException("El usuario no tiene permisos para publicar productos");
        }

        product.setVendedor(vendedor);
        return productRepository.save(product);
    }

}
