package bargamingBackend.bargaming.modules.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateRequest {

    @Size(max = 100)
    private String username;

    @Email @Size(max = 100)
    private String email;

    @Size(max = 255)
    private String direccion;

    @Size(max = 20)
    private String phone;
}