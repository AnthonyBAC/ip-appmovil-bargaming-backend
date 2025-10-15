package bargamingBackend.bargaming.modules.ventas.model;

import bargamingBackend.bargaming.modules.products.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DETALLE_VENTAS")
@SequenceGenerator(name = "detalle_venta_seq", sequenceName = "detalle_venta_seq", allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalle_venta_seq")
    private Long detalleId;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Product producto;

    private Integer cantidad;

    private Integer precioUnitario;

    private Integer subtotal;
}
