package bargamingBackend.bargaming.modules.auth.service;

import bargamingBackend.bargaming.common.enums.Role;
import bargamingBackend.bargaming.modules.auth.model.User;

import java.util.List;
import java.util.Optional;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    User saveUser(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    List<User> findAll();

    User updateUserRole(Long userId, Role newRole);

    String uploadProfileImage(MultipartFile file) throws IOException;

}
