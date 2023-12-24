package springsecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * {@code UserNotFoundException} is an exception class representing the scenario where a user is not found.
 *
 * <p>
 * This exception is annotated with {@link ResponseStatus} to indicate that, when thrown, it results in an HTTP 404 Not Found response.
 * The reason for the exception is set to "User not found" with the corresponding HTTP status code.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 * try {
 *     // Code that may throw UserNotFoundException
 * } catch (UserNotFoundException ex) {
 *     // Handle the exception, e.g., return a 404 Not Found response
 *     return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
 * }
 * </pre>
 * </p>
 *
 * @version 1.0
 * @since 2023-12-24
 */
@ResponseStatus(reason = "User not found", value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@code UserNotFoundException} with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
