package springsecurity.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

/**
 * This class handles unsuccessful JWT authentication.
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final HandlerExceptionResolver resolver;

    /**
     * Constructs a new {@code CustomAuthenticationEntryPoint} with the specified {@link HandlerExceptionResolver}.
     *
     * @param resolver The {@link HandlerExceptionResolver} used to handle exceptions.
     */
    public CustomAuthenticationEntryPoint(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    /**
     * Handles an unsuccessful JWT authentication scenario by delegating the exception to the configured {@link HandlerExceptionResolver}.
     *
     * @param request The {@link HttpServletRequest} representing the failed authentication request.
     * @param response The {@link HttpServletResponse} to be populated with the handled response.
     * @param authException The {@link AuthenticationException} representing the failed authentication.
     * @throws IOException if an I/O error occurs.
     * @throws ServletException if a servlet-related error occurs.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        this.resolver.resolveException(request, response, null, authException);
    }
}
