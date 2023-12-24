package springsecurity.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    @Value("${jwt.Secret-Key}")
    private String token;
    @Value("${jwt.Expiration-Time}")
    private Integer expirationTime;

    /**
     * Generates a JWT token for the specified username.
     *
     * @param username The username for which the token is generated.
     * @return A JWT token as a {@link String}.
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    /**
     * Validates a JWT token for the specified user details.
     *
     * @param token The JWT token to validate.
     * @param userDetails The {@link UserDetails} object representing user details.
     * @return {@code true} if the token is valid; otherwise, {@code false}.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        Date expirationDate = getExpirationDateFromToken(token);
        return username.equals(userDetails.getUsername()) && expirationDate.after(new Date());
    }

    /**
     * Extracts the username from a JWT token.
     *
     * @param token The JWT token from which to extract the username.
     * @return The username as a {@link String}.
     */
    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    /**
     * Obtains the expiration date from a JWT token.
     *
     * @param token The JWT token from which to obtain the expiration date.
     * @return The expiration date as a {@link Date} object.
     */
    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Claims getAllClaimsFromToken(String token)  {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime * 1000 * 60))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(token);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
