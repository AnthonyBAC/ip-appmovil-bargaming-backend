package bargamingBackend.bargaming.modules.auth.dto;

import bargamingBackend.bargaming.common.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateRequest {

    @NotBlank @Size(max = 100)
    private String username;

    @NotBlank @Email @Size(max = 100)
    private String email;

    @Size(max = 255)
    private String direccion;

    @Size(max = 20)
    private String phone;

    @NotBlank @Size(min = 8, max = 255)
    private String password;

    @NotNull
    private Role role;
}
