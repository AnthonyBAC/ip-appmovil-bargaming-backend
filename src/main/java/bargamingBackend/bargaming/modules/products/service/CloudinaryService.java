package bargamingBackend.bargaming.modules.products.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService() {

        this.cloudinary = new Cloudinary(System.getenv(
                "CLOUDINARY_URL"));
    }

    public String uploadImage(MultipartFile file, String publicId) throws IOException {
        Map<String, Object> options = ObjectUtils.asMap("public_id", publicId, "overwrite", true);
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), options);
        return (String) uploadResult.get("secure_url");
    }

}
