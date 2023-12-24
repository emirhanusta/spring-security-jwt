package springsecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * {@code UserAlreadyExistException} is an exception class representing the scenario where a user already exists.
 *
 * <p>
 * This exception is annotated with {@link ResponseStatus} to indicate that, when thrown, it results in an HTTP 409 Conflict response.
 * The reason for the exception is set to "User already exists" with the corresponding HTTP status code.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 * try {
 *     // Code that may throw UserAlreadyExistException
 * } catch (UserAlreadyExistException ex) {
 *     // Handle the exception, e.g., return a 409 Conflict response
 *     return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
 * }
 * </pre>
 * </p>
 *
 * @version 1.0
 * @since 2023-12-24
 */
@ResponseStatus(reason = "User already exists", value = HttpStatus.CONFLICT)
public class UserAlreadyExistException extends RuntimeException {

    /**
     * Constructs a new {@code UserAlreadyExistException} with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     */
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
