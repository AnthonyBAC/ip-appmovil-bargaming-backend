package bargamingBackend.bargaming.modules.auth.service.impl;

import bargamingBackend.bargaming.common.enums.Role;
import bargamingBackend.bargaming.modules.auth.model.User;
import bargamingBackend.bargaming.modules.auth.repository.UserRepository;
import bargamingBackend.bargaming.modules.auth.service.UserService;
import bargamingBackend.bargaming.modules.products.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CloudinaryService cloudinaryService) {
        this.userRepository = userRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public User saveUser(User user) {
        if (user.getRole() == null) {
            user.setRole(Role.CLIENTE);
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User updateUserRole(Long userId, Role newRole) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + userId));

        user.setRole(newRole);
        return userRepository.save(user);
    }

    @Override
    public String uploadProfileImage(MultipartFile file, String email) throws IOException {
        String uniqueId = "profile_" + email.replace("@", "_").replace(".", "_");
        return cloudinaryService.uploadImage(file, uniqueId);
    }
}
