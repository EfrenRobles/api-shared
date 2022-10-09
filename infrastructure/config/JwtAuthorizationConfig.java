package api.shared.infrastructure.config;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtAuthorizationConfig extends OncePerRequestFilter {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";

    public static JwtAuthorizationConfig build() {

        return new JwtAuthorizationConfig();
    }

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain
    ) throws ServletException, IOException {

        try {

            if (!existJWTToken(request, response)) {
                SecurityContextHolder.clearContext();

                return;
            }

            Claims claims = validateToken(request);

            if (claims.get("authorities") != null) {
                setUpSpringAuthentication(claims);

                return;
            }

            SecurityContextHolder.clearContext();
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        } finally {
            chain.doFilter(request, response);
        }
    }

    private Claims validateToken(HttpServletRequest request) {

        String jwtToken = request.getHeader(HEADER).replace(PREFIX, "");

        return Jwts
            .parserBuilder()
            .setSigningKey(EnvConfig.getProperty("spring.client.secretKey").getBytes())
            .build()
            .parseClaimsJws(jwtToken)
            .getBody();
    }

    private boolean existJWTToken(HttpServletRequest request, HttpServletResponse res) {

        String authenticationHeader = request.getHeader(HEADER);

        if (authenticationHeader == null || !authenticationHeader.startsWith(PREFIX)) {
            return false;
        }

        return true;
    }

    /**
     * To authenticate inside of the flow of Spring
     * 
     * @param claims
     */
    private void setUpSpringAuthentication(Claims claims) {

        SimpleGrantedAuthority authoritie = claims.values().stream()
            .map(c -> new SimpleGrantedAuthority(c.toString()))
            .findFirst()
            .get();

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
            claims.getSubject(),
            null,
            Arrays.asList(authoritie)
        );

        SecurityContextHolder
            .getContext()
            .setAuthentication(auth);
    }
}