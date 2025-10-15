package bargamingBackend.bargaming.modules.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/client")
public class ClientController {

    @GetMapping("/demo")
    public ResponseEntity<?> clientDemo() {
        return ResponseEntity.ok("✅ Bienvenido CLIENTE, acceso concedido");
    }
}
