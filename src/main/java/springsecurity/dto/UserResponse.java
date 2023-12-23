package springsecurity.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserResponse(
    UUID id,
    String username,
    String role
) {
}
