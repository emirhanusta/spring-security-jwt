package springsecurity.dto;

public record LoginRequest(
    String username,
    String password
) {
}
