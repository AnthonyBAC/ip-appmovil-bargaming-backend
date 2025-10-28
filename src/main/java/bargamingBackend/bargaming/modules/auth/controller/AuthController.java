package bargamingBackend.bargaming.modules.auth.controller;

import bargamingBackend.bargaming.common.dto.LoginRequest;
import bargamingBackend.bargaming.modules.auth.dto.UserCreateRequest;
import bargamingBackend.bargaming.modules.auth.model.User;
import bargamingBackend.bargaming.modules.auth.service.JWTService;
import bargamingBackend.bargaming.modules.auth.service.UserService;
import jakarta.validation.Valid;

import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
            user.setPassword(passwordEncoder.encode(request.getPassword()));
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
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("message", "Correo no registrado"));
            }

            User user = userOpt.get();

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("message", "Contraseña incorrecta"));
            }

            String token = jwtService.generateToken(user);

            return ResponseEntity.ok(Map.of(
                    "message", "Login exitoso",
                    "token", token,
                    "email", user.getEmail(),
                    "role", user.getRole(),
                    "username", user.getUsername()));

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Error al iniciar sesión: " + e.getMessage()));
        }
    }

    @PostMapping("/upload-profile")
    public ResponseEntity<?> uploadProfileImage(@RequestParam("file") MultipartFile file, Principal principal) {
        try {
            // Obtener usuario autenticado
            User user = userService.findByEmail(principal.getName())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // Subir imagen (Cloudinary o local)
            String imageUrl = userService.uploadProfileImage(file);

            // Guardar en DB
            user.setProfileImageUrl(imageUrl);
            userService.saveUser(user);

            return ResponseEntity.ok(Map.of(
                    "message", "Imagen subida correctamente",
                    "profileImageUrl", imageUrl));

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "Error al subir imagen: " + e.getMessage()));
        }
    }

}
