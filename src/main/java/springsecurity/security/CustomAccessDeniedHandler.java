package springsecurity.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import org.springframework.security.access.AccessDeniedException;

/**
 * This class handles access denied scenarios.
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final HandlerExceptionResolver resolver;

    /**
     * Constructs a new {@code CustomAccessDeniedHandler} with the specified {@link HandlerExceptionResolver}.
     *
     * @param resolver The {@link HandlerExceptionResolver} used to handle exceptions.
     */
    public CustomAccessDeniedHandler(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    /**
     * Handles an access denied scenario by delegating the exception to the configured {@link HandlerExceptionResolver}.
     *
     * @param request The {@link HttpServletRequest} representing the denied request.
     * @param response The {@link HttpServletResponse} to be populated with the handled response.
     * @param accessDeniedException The {@link AccessDeniedException} representing the access denied situation.
     * @throws IOException if an I/O error occurs.
     * @throws ServletException if a servlet-related error occurs.
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        this.resolver.resolveException(request, response, null, accessDeniedException);
    }
}
