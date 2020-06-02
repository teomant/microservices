package crow.teomant.gateway.auth.security.service.impl;

import crow.teomant.gateway.auth.user.dto.RoleDto;
import crow.teomant.gateway.auth.user.dto.UserDto;
import crow.teomant.gateway.auth.user.entity.User;
import crow.teomant.gateway.auth.jwt.JwtTokenProvider;
import crow.teomant.gateway.auth.jwt.entity.JwtToken;
import crow.teomant.gateway.auth.jwt.repository.JwtTokenRepository;
import crow.teomant.gateway.auth.security.service.LoginService;
import crow.teomant.gateway.auth.user.repository.UserRepository;
import crow.teomant.gateway.auth.user.service.UserService;
import crow.teomant.gateway.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtTokenRepository jwtTokenRepository;

    public LoginServiceImpl(PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, UserService userService, UserRepository userRepository, JwtTokenRepository jwtTokenRepository) {
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.userRepository = userRepository;
        this.jwtTokenRepository = jwtTokenRepository;
    }

    @Override
    public String login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,
                    password));
            UserDto user = userService.getUserByUsername(username);
            if (user == null || user.getRole() == null || user.getRole().isEmpty()) {
                throw new CustomException("Invalid username or password.", HttpStatus.UNAUTHORIZED);
            }
            //NOTE: normally we dont need to add "ROLE_" prefix. Spring does automatically for us.
            //Since we are using custom token using JWT we should add ROLE_ prefix
            String token = jwtTokenProvider.createToken(username, user.getRole().stream()
                    .map((RoleDto role) -> "ROLE_" + role.getRole()).filter(Objects::nonNull).collect(Collectors.toList()));
            return token;

        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username or password.", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public boolean logout(String token) {
        jwtTokenRepository.delete(new JwtToken(token));
        return true;
    }

    @Override
    public Boolean isValidToken(String token) {
        return jwtTokenProvider.validateToken(token);
    }

    @Override
    public String createNewToken(String token) {
        String username = jwtTokenProvider.getUsername(token);
        List<String> roleList = jwtTokenProvider.getRoleList(token);
        String newToken = jwtTokenProvider.createToken(username, roleList);
        return newToken;
    }
}