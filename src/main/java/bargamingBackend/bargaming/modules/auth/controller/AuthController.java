package bargamingBackend.bargaming.modules.auth.controller;

import bargamingBackend.bargaming.common.dto.LoginRequest;
import bargamingBackend.bargaming.modules.auth.dto.UserCreateRequest;
import bargamingBackend.bargaming.modules.auth.model.User;
import bargamingBackend.bargaming.modules.auth.service.JWTService;
import bargamingBackend.bargaming.modules.auth.service.UserService;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JWTService jwtService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AuthController(UserService userService, JWTService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserCreateRequest request) {
        try {
            User user = new User();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setDireccion(request.getDireccion());
            user.setPhone(request.getPhone());
            user.setPassword(passwordEncoder.encode(request.getPassword())); // ✅ Encriptar aquí
            user.setRole(request.getRole());

            User savedUser = userService.saveUser(user);

            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);

        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest request) {
        try {
            var userOpt = userService.findByEmail(request.getEmail());
            if (userOpt.isEmpty()) {
                return new ResponseEntity<>("Usuario no encontrado", HttpStatus.NOT_FOUND);
            }

            User user = userOpt.get();

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return new ResponseEntity<>("Contraseña incorrecta", HttpStatus.UNAUTHORIZED);
            }

            String token = jwtService.generateToken(user);

            return ResponseEntity.ok(Map.of(
                    "message", "Login exitoso",
                    "token", token,
                    "email", user.getEmail(),
                    "role", user.getRole()));

        } catch (Exception e) {
            return new ResponseEntity<>("Error al iniciar sesión: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
