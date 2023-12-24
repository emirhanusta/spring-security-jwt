package springsecurity.dto;

import lombok.Builder;


@Builder
public record TokenResponse(
    UserResponse user,
    String token
) {
}
