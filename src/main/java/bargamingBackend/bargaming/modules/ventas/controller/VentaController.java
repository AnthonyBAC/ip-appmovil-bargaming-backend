package bargamingBackend.bargaming.modules.ventas.controller;

import bargamingBackend.bargaming.common.enums.EstadoVenta;
import bargamingBackend.bargaming.modules.ventas.model.DetalleVenta;
import bargamingBackend.bargaming.modules.ventas.model.Venta;
import bargamingBackend.bargaming.modules.ventas.service.VentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @PostMapping("/{clienteId}")
    public ResponseEntity<Venta> registrarVenta(@PathVariable Long clienteId) {
        return ResponseEntity.ok(ventaService.registrarVentaDesdeCarrito(clienteId));
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Venta>> listarComprasCliente(@PathVariable Long idCliente) {
        return ResponseEntity.ok(ventaService.listarComprasCliente(idCliente));
    }

    @GetMapping("/vendedor/{idVendedor}")
    public ResponseEntity<List<Venta>> listarVentasVendedor(@PathVariable Long idVendedor) {
        return ResponseEntity.ok(ventaService.listarVentasVendedor(idVendedor));
    }

    @GetMapping("/{id}/detalle")
    public ResponseEntity<List<DetalleVenta>> obtenerDetalleVenta(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.obtenerDetalleVenta(id));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Venta> actualizarEstado(
            @PathVariable Long id,
            @RequestParam EstadoVenta estado) {
        return ResponseEntity.ok(ventaService.actualizarEstado(id, estado));
    }
}
