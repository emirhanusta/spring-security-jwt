package springsecurity.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record TokenResponse(
    UUID id,
    String username,
    String role,
    String token
) {
}
