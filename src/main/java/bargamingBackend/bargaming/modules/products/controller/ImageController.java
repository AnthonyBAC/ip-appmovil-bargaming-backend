package bargamingBackend.bargaming.modules.products.controller;

import bargamingBackend.bargaming.modules.products.model.ImageProduct;
import bargamingBackend.bargaming.modules.products.repository.ImageProductRepository;
import bargamingBackend.bargaming.modules.products.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private ImageProductRepository imageRepository;

    @PostMapping("/upload")
    public ResponseEntity<ImageProduct> uploadImage(@RequestParam("file") MultipartFile file,
            @RequestParam("productId") Long productId) throws IOException {

        String url = cloudinaryService.uploadImage(file);

        ImageProduct img = new ImageProduct();
        img.setFileName(file.getOriginalFilename());
        img.setUrl(url);
        img.setProductId(productId);

        ImageProduct saved = imageRepository.save(img);
        return ResponseEntity.ok(saved);
    }

}
