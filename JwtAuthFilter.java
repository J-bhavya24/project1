package com.wipro.auth.config;

import com.wipro.auth.service.UserDetailsServiceImpl;
import com.wipro.auth.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JwtAuthFilter
 *
 * Runs ONCE per request (extends OncePerRequestFilter).
 *
 * What it does:
 *  1. Reads the Authorization header: "Bearer <token>"
 *  2. Extracts and validates the JWT
 *  3. Loads the user from DB
 *  4. Sets the authentication in SecurityContext
 *     → Spring Security now knows who the user is for this request
 *
 * If no JWT is present or it is invalid, the filter just passes through
 * and Spring Security will reject the request at the authorization step.
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Read Authorization header
        String authHeader = request.getHeader("Authorization");

        String token = null;
        String username = null;

        // Header must be: "Bearer <token>"
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // remove "Bearer "
            try {
                username = jwtUtil.extractUsername(token);
            } catch (Exception e) {
                // Invalid token format — just continue, will be rejected later
            }
        }

        // 2. If we got a username and SecurityContext has no auth yet
        if (username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            // 3. Load user from DB
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // 4. Validate token
            if (jwtUtil.validateToken(token)) {

                // 5. Create authentication token and set in SecurityContext
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 6. Continue the filter chain
        filterChain.doFilter(request, response);
    }
}
