package bargamingBackend.bargaming.modules.products.controller;

import bargamingBackend.bargaming.modules.products.model.ImageProduct;
import bargamingBackend.bargaming.modules.products.model.Product;
import bargamingBackend.bargaming.modules.products.service.ImageProductService;
import bargamingBackend.bargaming.modules.products.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductController {

    private final ProductService productService;
    private final ImageProductService imageService;

    public ProductController(ProductService productService, ImageProductService imageService) {
        this.productService = productService;
        this.imageService = imageService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) String nombre) {
        return ResponseEntity.ok(productService.getFilteredProducts(categoria, marca, nombre));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/imagenes")
    public ResponseEntity<List<ImageProduct>> getImages(@PathVariable Long id) {
        return ResponseEntity.ok(imageService.getImagesByProductId(id));
    }

    @PostMapping("/{id}/imagenes")
    public ResponseEntity<ImageProduct> uploadImage(@PathVariable Long id, @RequestBody ImageProduct image) {
        return ResponseEntity.ok(imageService.saveImageForProduct(id, image));
    }
}
