package crow.teomant.gateway.auth.user.service;


import crow.teomant.gateway.auth.user.dto.UserDto;

public interface UserService {

    UserDto getUserByUsername(String username);
}