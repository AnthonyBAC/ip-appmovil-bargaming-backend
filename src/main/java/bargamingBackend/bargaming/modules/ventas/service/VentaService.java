package bargamingBackend.bargaming.modules.ventas.service;

import bargamingBackend.bargaming.modules.auth.model.User;
import bargamingBackend.bargaming.modules.auth.repository.UserRepository;
import bargamingBackend.bargaming.modules.cart.model.Cart;
import bargamingBackend.bargaming.modules.cart.repository.CartRepository;
import bargamingBackend.bargaming.modules.products.model.Product;
import bargamingBackend.bargaming.modules.ventas.model.DetalleVenta;
import bargamingBackend.bargaming.common.enums.EstadoVenta;
import bargamingBackend.bargaming.modules.ventas.model.Venta;
import bargamingBackend.bargaming.modules.ventas.repository.DetalleVentaRepository;
import bargamingBackend.bargaming.modules.ventas.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public VentaService(VentaRepository ventaRepository, DetalleVentaRepository detalleVentaRepository,
            CartRepository cartRepository, UserRepository userRepository) {
        this.ventaRepository = ventaRepository;
        this.detalleVentaRepository = detalleVentaRepository;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    public Venta registrarVentaDesdeCarrito(Long clienteId) {
        User cliente = userRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        List<Cart> carrito = cartRepository.findByCliente_UserId(clienteId);
        if (carrito.isEmpty())
            throw new RuntimeException("El carrito está vacío");

        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setEstado(EstadoVenta.PAGADA);

        List<DetalleVenta> detalles = new ArrayList<>();
        int total = 0;

        for (Cart item : carrito) {
            Product producto = item.getProduct();

            // Crear detalle
            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(venta);
            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setSubtotal(producto.getPrecio() * item.getCantidad());
            detalles.add(detalle);

            total += detalle.getSubtotal();
        }

        venta.setTotal(total);
        venta.setDetalles(detalles);

        Venta savedVenta = ventaRepository.save(venta);

        cartRepository.deleteAll(carrito);

        return savedVenta;
    }

    public List<Venta> listarComprasCliente(Long clienteId) {
        return ventaRepository.findByCliente_UserId(clienteId);
    }

    public List<Venta> listarVentasVendedor(Long vendedorId) {
        return ventaRepository.findByVendedor_UserId(vendedorId);
    }

    public List<DetalleVenta> obtenerDetalleVenta(Long ventaId) {
        return detalleVentaRepository.findByVenta_VentaId(ventaId);
    }

    public Venta actualizarEstado(Long ventaId, EstadoVenta nuevoEstado) {
        Venta venta = ventaRepository.findById(ventaId)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        venta.setEstado(nuevoEstado);
        return ventaRepository.save(venta);
    }
}
