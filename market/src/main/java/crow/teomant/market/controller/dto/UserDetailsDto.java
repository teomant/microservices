package crow.teomant.market.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailsDto {

    private String username;

    private Set<RoleDto> roles;
}
