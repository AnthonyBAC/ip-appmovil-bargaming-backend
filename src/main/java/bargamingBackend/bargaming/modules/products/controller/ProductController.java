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
            @RequestParam(value = "recibeOfertas", required = false) Boolean recibeOfertas)
            
            throws IOException {


        Product product = productService.createProductWithImage(
                file, nombre, marca, categoria, precio,
                recibeOfertas != null && recibeOfertas
        );

        return ResponseEntity.ok(product);
    }
}
