package bargamingBackend.bargaming.modules.products.service;

import bargamingBackend.bargaming.modules.products.model.Product;
import bargamingBackend.bargaming.modules.products.repository.ProductRepository;
import bargamingBackend.bargaming.modules.auth.model.User;
import bargamingBackend.bargaming.modules.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    public Product createProductWithImage(MultipartFile file, String nombre, String marca,
                                          String categoria, Integer precio, Boolean recibeOfertas,
                                          String emailVendedor) throws IOException {

        User vendedor = userRepository.findByEmail(emailVendedor)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String imageUrl = cloudinaryService.uploadImage(file);

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
}
