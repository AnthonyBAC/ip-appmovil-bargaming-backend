package bargamingBackend.bargaming.modules.products.service;

import bargamingBackend.bargaming.modules.products.model.ImageProduct;
import bargamingBackend.bargaming.modules.products.repository.ImageProductRepository;
import bargamingBackend.bargaming.modules.products.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageProductService {

    private final ImageProductRepository imageRepo;

    public ImageProductService(ImageProductRepository imageRepo, ProductRepository productRepo) {
        this.imageRepo = imageRepo;
    }

    public List<ImageProduct> getImagesByProductId(Long productId) {
        return imageRepo.findByProduct_ProductId(productId);
    }

}
