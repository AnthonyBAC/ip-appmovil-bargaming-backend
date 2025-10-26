package bargamingBackend.bargaming.modules.products.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "IMAGE_PRODUCTS")
@SequenceGenerator(name = "image_product_seq", sequenceName = "IMAGE_PRODUCT_SEQ", allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_product_seq")
    private Long imageId;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false, length = 1000)
    private String url;

    @Column(name = "product_id", nullable = false)
    private Long productId;

}
