package bargamingBackend.bargaming.modules.ventas.repository;

import bargamingBackend.bargaming.modules.ventas.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    List<DetalleVenta> findByVenta_VentaId(Long ventaId);
}
