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

    /**
     * Registers a new user based on the provided registration request.
     *
     * @param request The {@link RegisterRequest} containing user registration details.
     * @return A {@link UserResponse} representing the registered user.
     * @throws UserAlreadyExistException if the username already exists.
     */
    public UserResponse register(RegisterRequest request) {
        if (userService.existsByUsername(request.username())) {
            throw new UserAlreadyExistException("Username already exists");
        }
        return userService.saveUser(User.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.valueOf(request.role()))
                .build());
    }

    /**
     * Authenticates a user based on the provided login request.
     *
     * @param request The {@link LoginRequest} containing user login details.
     * @return A {@link TokenResponse} containing the user and generated authentication token.
     * @throws BadCredentialsException if the provided credentials are incorrect.
     */
    public TokenResponse login(LoginRequest request) {
        UserResponse userResponse = userService.findByUsername(request.username());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password()));
            return TokenResponse.builder()
                    .user(userResponse)
                    .token(tokenService.generateToken(request.username()))
                    .build();
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        }
    }
}
