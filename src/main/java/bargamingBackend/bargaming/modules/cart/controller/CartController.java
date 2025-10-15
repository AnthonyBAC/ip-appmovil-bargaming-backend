package bargamingBackend.bargaming.modules.cart.controller;

import bargamingBackend.bargaming.modules.cart.model.Cart;
import bargamingBackend.bargaming.modules.cart.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<List<Cart>> getCart(@PathVariable Long clienteId) {
        return ResponseEntity.ok(cartService.getCartByCliente(clienteId));
    }

    @PostMapping("/{clienteId}/agregar/{productoId}")
    public ResponseEntity<Cart> addToCart(
            @PathVariable Long clienteId,
            @PathVariable Long productoId,
            @RequestParam(defaultValue = "1") Integer cantidad) {

        return ResponseEntity.ok(cartService.addToCart(clienteId, productoId, cantidad));
    }

    @PutMapping("/{cartId}")
    public ResponseEntity<Cart> updateQuantity(
            @PathVariable Long cartId,
            @RequestParam Integer cantidad) {

        return ResponseEntity.ok(cartService.updateQuantity(cartId, cantidad));
    }

    @DeleteMapping("/item/{cartId}")
    public ResponseEntity<Void> removeItem(@PathVariable Long cartId) {
        cartService.removeFromCart(cartId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{clienteId}/vaciar")
    public ResponseEntity<Void> clearCart(@PathVariable Long clienteId) {
        cartService.clearCart(clienteId);
        return ResponseEntity.noContent().build();
    }
}
