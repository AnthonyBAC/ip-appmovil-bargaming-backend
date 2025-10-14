package bargamingBackend.bargaming.modules.auth.dto;

import bargamingBackend.bargaming.common.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long userId;
    private String username;
    private String email;
    private String direccion;
    private String phone;
    private Role role;
 }