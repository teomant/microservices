package crow.teomant.gateway.auth.user.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {

    private Long id;

    private String email;

    private String username;

    private String password;

    private Integer active = 1;

    private boolean isLocked = false;

    private boolean isExpired = false;

    private boolean isEnabled = true;

    private Set<RoleDto> role = new HashSet<>();
}