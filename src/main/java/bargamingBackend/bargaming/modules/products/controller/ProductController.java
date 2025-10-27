package bargamingBackend.bargaming.modules.products.controller;

import bargamingBackend.bargaming.modules.products.model.Product;
import bargamingBackend.bargaming.modules.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/create-with-image", consumes = "multipart/form-data")
    @PreAuthorize("hasRole('VENDEDOR') or hasRole('ADMIN')")
    public ResponseEntity<Product> createProductWithImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("nombre") String nombre,
            @RequestParam("marca") String marca,
            @RequestParam("categoria") String categoria,
            @RequestParam("precio") Integer precio,
            @RequestParam(value = "recibeOfertas", required = false) Boolean recibeOfertas,
            Principal principal) throws IOException {

        Product product = productService.createProductWithImage(
                file, nombre, marca, categoria, precio,
                recibeOfertas != null && recibeOfertas, principal);

        return ResponseEntity.ok(product);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('VENDEDOR') or hasRole('ADMIN')")
    public ResponseEntity<Product> createProduct(
            @RequestBody Product product,
            Principal principal) {

        Product savedProduct = productService.createProduct(product, principal);
        return ResponseEntity.ok(savedProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }


    @GetMapping("/vendedor/{id}")
    @PreAuthorize("hasRole('VENDEDOR' or hasRole('ADMIN'))")
    public ResponseEntity<List<Product>> getProductsBySeller(@PathVariable Long id) {
        List<Product> products = productService.getProductsBySeller(id);
        return ResponseEntity.ok(products);
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    try {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {
        return ResponseEntity.notFound().build();
    }
}
}
