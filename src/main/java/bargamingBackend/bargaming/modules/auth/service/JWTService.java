package bargamingBackend.bargaming.modules.auth.service;

import bargamingBackend.bargaming.modules.auth.model.User;

public interface JWTService {
    String generateToken(User user);

    boolean validateToken(String token);

    String extractEmail(String token);

    byte[] getSecretKeyBytes();

}
