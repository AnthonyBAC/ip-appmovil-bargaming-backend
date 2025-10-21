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

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostConstruct
    public void init() {

        if (!userRepository.existsByEmail("admin@bargaming.com")) {
            User admin = new User();
            admin.setUsername("AdminBG");
            admin.setEmail("admin@bargaming.com");
            admin.setDireccion("Oficina Central");
            admin.setPhone("111111111");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
            System.out.println(">>> Usuario ADMIN creado (admin@bargaming.com / admin123)");
        }

        if (!userRepository.existsByEmail("vendedor@bargaming.com")) {
            User vendedor = new User();
            vendedor.setUsername("VendedorBG");
            vendedor.setEmail("vendedor@bargaming.com");
            vendedor.setDireccion("Sucursal 1");
            vendedor.setPhone("222222222");
            vendedor.setPassword(passwordEncoder.encode("vendedor123"));
            vendedor.setRole(Role.VENDEDOR);
            userRepository.save(vendedor);
            System.out.println(">>> Usuario VENDEDOR creado (vendedor@bargaming.com / vendedor123)");
        }

        if (!userRepository.existsByEmail("cliente@bargaming.com")) {
            User cliente = new User();
            cliente.setUsername("ClienteBG");
            cliente.setEmail("cliente@bargaming.com");
            cliente.setDireccion("Dirección Cliente");
            cliente.setPhone("333333333");
            cliente.setPassword(passwordEncoder.encode("cliente123"));
            cliente.setRole(Role.CLIENTE);
            userRepository.save(cliente);
            System.out.println(">>> Usuario CLIENTE creado (cliente@bargaming.com / cliente123)");
        }
    }
}
