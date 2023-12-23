package springsecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springsecurity.dto.TokenResponse;
import springsecurity.dto.LoginRequest;
import springsecurity.dto.RegisterRequest;
import springsecurity.dto.UserResponse;
import springsecurity.exception.UserAlreadyExistException;
import springsecurity.model.Role;
import springsecurity.model.User;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    public UserResponse register(RegisterRequest request) {
        if (userService.existsByUsername(request.username())) {
            throw new UserAlreadyExistException("Username already exists");
        }
        User user = userService.saveUser(User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.valueOf(request.role()))
                .build());
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();
    }

    public TokenResponse login(LoginRequest request) {
        User user = userService.findByUsername(request.username());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password()));
            return TokenResponse.builder()
                    .id(user.getId())
                    .username(request.username())
                    .role(user.getRole().name())
                    .token(tokenService.generateToken(request.username()))
                    .build();
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        }
    }
}
