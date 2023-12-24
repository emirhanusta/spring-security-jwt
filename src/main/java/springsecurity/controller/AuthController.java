package springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springsecurity.dto.TokenResponse;
import springsecurity.dto.LoginRequest;
import springsecurity.dto.RegisterRequest;
import springsecurity.dto.UserResponse;
import springsecurity.service.AuthService;

/**
 * {@code AuthController} is the Spring Security Controller managing user authentication processes.
 *
 * <p>
 * This controller includes endpoints for user registration, login, and special endpoints for users with different authorities.
 * </p>
 *
 * @version 1.0
 * @since 2023-12-24
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * Constructor for the {@code AuthController} class.
     *
     * @param authService An instance of {@link AuthService}, the service handling authentication processes.
     */
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Endpoint for user registration.
     *
     * @param request An instance of {@link RegisterRequest} containing necessary information for a new user registration.
     * @return {@link ResponseEntity} representing the HTTP response containing information about the registered user.
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok().body(authService.register(request));
    }

    /**
     * Endpoint for user login.
     *
     * @param request An instance of {@link LoginRequest} containing necessary information for user login.
     * @return {@link ResponseEntity} representing the HTTP response containing login information for the logged-in user.
     */
    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok().body(authService.login(request));
    }

    /**
     * Endpoint returning "Hello Admin" message for users with the "ADMIN" authority.
     *
     * @return {@link ResponseEntity} representing the HTTP response containing the "Hello Admin" message.
     */
    @GetMapping("/admin")
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok().body("Hello Admin");
    }

    /**
     * Endpoint returning "Hello User" message for normal users.
     *
     * @return {@link ResponseEntity} representing the HTTP response containing the "Hello User" message.
     */
    @GetMapping("/user")
    public ResponseEntity<String> user() {
        return ResponseEntity.ok().body("Hello User");
    }

    /**
     * Endpoint returning "PreAuthorize Test" message for users with the "ADMIN" authority.
     *
     * @return {@link ResponseEntity} representing the HTTP response containing the "PreAuthorize Test" message.
     */
    @GetMapping("/test")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok().body("PreAuthorize Test");
    }
}
