package springsecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles the {@code UserAlreadyExistException} and returns a Conflict (409) response.
     *
     * @param ex The exception to be handled.
     * @return {@link ResponseEntity} with a Conflict status code and the exception message.
     */
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    /**
     * Handles the {@code UserNotFoundException} and returns a Not Found (404) response.
     *
     * @param ex The exception to be handled.
     * @return {@link ResponseEntity} with a Not Found status code and the exception message.
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles the {@code UsernameNotFoundException} and returns a Not Found (404) response.
     *
     * @param ex The exception to be handled.
     * @return {@link ResponseEntity} with a Not Found status code and the exception message.
     */
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles the {@code SignatureException} and returns an Unauthorized (401) response.
     *
     * @param ex The exception to be handled.
     * @return {@link ResponseEntity} with an Unauthorized status code and the exception message.
     */
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<Object> handleJwtException(SignatureException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles the {@code ExpiredJwtException} and returns an Unauthorized (401) response.
     *
     * @param ex The exception to be handled.
     * @return {@link ResponseEntity} with an Unauthorized status code and the exception message.
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Object> handleJwtException(ExpiredJwtException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles the {@code BadCredentialsException} and returns an Unauthorized (401) response.
     *
     * @param ex The exception to be handled.
     * @return {@link ResponseEntity} with an Unauthorized status code and the exception message.
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles the {@code InsufficientAuthenticationException} and returns an Unauthorized (401) response.
     *
     * @param ex The exception to be handled.
     * @return {@link ResponseEntity} with an Unauthorized status code and the exception message.
     */
    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<Object> handleInsufficientAuthenticationException(InsufficientAuthenticationException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles the {@code AccessDeniedException} and returns a Forbidden (403) response.
     *
     * @param ex The exception to be handled.
     * @return {@link ResponseEntity} with a Forbidden status code and the exception message.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    /**
     * Handles generic exceptions and returns an Internal Server Error (500) response.
     *
     * @param ex The exception to be handled.
     * @return {@link ResponseEntity} with an Internal Server Error status code and the exception message.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
