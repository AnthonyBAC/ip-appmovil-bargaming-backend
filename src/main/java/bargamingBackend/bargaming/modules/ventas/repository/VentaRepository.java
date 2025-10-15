package bargamingBackend.bargaming.modules.ventas.repository;

import bargamingBackend.bargaming.modules.ventas.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    List<Venta> findByCliente_UserId(Long clienteId);

    List<Venta> findByVendedor_UserId(Long vendedorId);
}
