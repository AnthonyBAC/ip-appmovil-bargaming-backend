package bargamingBackend.bargaming.modules.ventas.model;

import bargamingBackend.bargaming.modules.auth.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import bargamingBackend.bargaming.common.enums.EstadoVenta;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "VENTAS")
@SequenceGenerator(name = "venta_seq", sequenceName = "venta_seq", allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venta_seq")
    private Long ventaId;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private User cliente;

    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private User vendedor;

    private LocalDateTime fechaVenta = LocalDateTime.now();

    private Integer total;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EstadoVenta estado = EstadoVenta.PAGADA;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalles;
}
