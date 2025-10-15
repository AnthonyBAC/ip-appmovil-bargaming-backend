package bargamingBackend.bargaming.modules.products.service;

import bargamingBackend.bargaming.modules.products.model.ImageProduct;
import bargamingBackend.bargaming.modules.products.model.Product;
import bargamingBackend.bargaming.modules.products.repository.ImageProductRepository;
import bargamingBackend.bargaming.modules.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageProductService {

    private final ImageProductRepository imageRepo;
    private final ProductRepository productRepo;

    public ImageProductService(ImageProductRepository imageRepo, ProductRepository productRepo) {
        this.imageRepo = imageRepo;
        this.productRepo = productRepo;
    }

    public List<ImageProduct> getImagesByProductId(Long productId) {
        return imageRepo.findByProduct_ProductId(productId);
    }

    public ImageProduct saveImageForProduct(Long productId, ImageProduct image) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        image.setProduct(product);
        return imageRepo.save(image);
    }
}
