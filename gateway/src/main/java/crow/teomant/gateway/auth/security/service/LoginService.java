package crow.teomant.gateway.auth.security.service;

import crow.teomant.gateway.auth.user.entity.User;

public interface LoginService {

    String login(String username, String password);

    User saveUser(User user);

    boolean logout(String token);

    Boolean isValidToken(String token);

    String createNewToken(String token);
}
