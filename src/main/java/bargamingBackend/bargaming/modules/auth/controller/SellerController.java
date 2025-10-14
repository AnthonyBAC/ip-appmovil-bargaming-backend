package bargamingBackend.bargaming.modules.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/seller")
public class SellerController {

    @GetMapping("/demo")
    public ResponseEntity<?> sellerDemo() {
        return ResponseEntity.ok("✅ Bienvenido VENDEDOR, acceso concedido");
    }
}
