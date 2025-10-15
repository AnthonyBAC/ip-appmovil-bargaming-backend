package bargamingBackend.bargaming.modules.cart.service;

import bargamingBackend.bargaming.modules.auth.model.User;
import bargamingBackend.bargaming.modules.cart.model.Cart;
import bargamingBackend.bargaming.modules.cart.repository.CartRepository;
import bargamingBackend.bargaming.modules.products.model.Product;
import bargamingBackend.bargaming.modules.products.repository.ProductRepository;
import bargamingBackend.bargaming.modules.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository,
            UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public List<Cart> getCartByCliente(Long clienteId) {
        return cartRepository.findByCliente_UserId(clienteId);
    }

    public Cart addToCart(Long clienteId, Long productId, Integer cantidad) {
        User cliente = userRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        Cart existing = cartRepository.findByCliente_UserIdAndProduct_ProductId(clienteId, productId);
        if (existing != null) {
            existing.setCantidad(existing.getCantidad() + cantidad);
            existing.setSubtotal(existing.getCantidad() * product.getPrecio());
            return cartRepository.save(existing);
        }

        Cart newItem = new Cart();
        newItem.setCliente(cliente);
        newItem.setProduct(product);
        newItem.setCantidad(cantidad);
        newItem.setSubtotal(cantidad * product.getPrecio());

        return cartRepository.save(newItem);
    }

    public Cart updateQuantity(Long cartId, Integer cantidad) {
        Cart item = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Ítem no encontrado en el carrito"));

        item.setCantidad(cantidad);
        item.setSubtotal(item.getProduct().getPrecio() * cantidad);
        return cartRepository.save(item);
    }

    public void removeFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    public void clearCart(Long clienteId) {
        List<Cart> items = cartRepository.findByCliente_UserId(clienteId);
        cartRepository.deleteAll(items);
    }
}
