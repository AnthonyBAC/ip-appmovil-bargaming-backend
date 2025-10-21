package bargamingBackend.bargaming.modules.auth.config;

import bargamingBackend.bargaming.common.enums.Role;
import bargamingBackend.bargaming.modules.auth.model.User;
import bargamingBackend.bargaming.modules.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {
        if (!userRepository.existsByEmail("dev@example.com")) {
            User dev = new User();
            dev.setUsername("developer");
            dev.setEmail("dev@example.com");
            dev.setDireccion("Laboratorio Bargaming");
            dev.setPhone("777777777");
            dev.setPassword(new BCryptPasswordEncoder().encode("12345678"));
            dev.setRole(Role.ADMIN);
            userRepository.save(dev);
            System.out.println(">>> Usuario DEV creado (dev@example.com / 12345678)");
        }
    }

    @PostConstruct
    public void init() {
        if (!userRepository.existsByEmail("dev@example.com")) {
            User dev = new User();
            dev.setUsername("developer");
            dev.setEmail("dev@example.com");
            dev.setDireccion("Laboratorio Bargaming");
            dev.setPhone("777777777");
            dev.setPassword(new BCryptPasswordEncoder().encode("12345678"));
            dev.setRole(Role.ADMIN);
            userRepository.save(dev);
            System.out.println(">>> Usuario DEV creado (dev@example.com / 12345678)");
        }
    }
}
