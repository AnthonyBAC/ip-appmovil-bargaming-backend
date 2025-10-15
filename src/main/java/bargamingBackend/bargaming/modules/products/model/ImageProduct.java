package bargamingBackend.bargaming.modules.products.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    @JsonBackReference
    private Product product;
}
