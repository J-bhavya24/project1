package com.wipro.auth.config;

import com.wipro.auth.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityConfig
 *
 * Configures two authentication methods:
 *
 *  A) LOCAL  → username + password → Spring Security → BCrypt check → JWT issued
 *  B) GITHUB → /oauth2/authorization/github → GitHub → callback → JWT issued
 *
 * Also registers the JwtAuthFilter so every request with a JWT is authenticated.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private OAuth2SuccessHandler oAuth2SuccessHandler;

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    // ── BCrypt Password Encoder ───────────────────────────────────────────────

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ── DAO Authentication Provider (reads from MySQL) ────────────────────────

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // ── Authentication Manager ────────────────────────────────────────────────

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // ── Security Filter Chain ─────────────────────────────────────────────────

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // Disable CSRF — REST API uses JWT, not browser sessions
            .csrf(csrf -> csrf.disable())

            // Sessions: STATELESS for API calls
            // IF_REQUIRED keeps OAuth2 redirect handshake working
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))

            // ── URL rules ────────────────────────────────────────────────────
            .authorizeHttpRequests(auth -> auth

                // Public endpoints
                .requestMatchers(
                    "/auth/register",
                    "/auth/login",
                    "/oauth2/**",
                    "/login/oauth2/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**"
                ).permitAll()

                // Everything else needs a valid JWT
                .anyRequest().authenticated()
            )

            // ── OAuth2 Login (GitHub) ────────────────────────────────────────
            .oauth2Login(oauth2 -> oauth2
                // When GitHub redirects back and login succeeds, call our handler
                .successHandler(oAuth2SuccessHandler)
            )

            // ── Authentication Provider ───────────────────────────────────────
            .authenticationProvider(authenticationProvider())

            // ── JWT Filter: validate JWT on every request ────────────────────
            // Runs before Spring Security's own UsernamePasswordAuthenticationFilter
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
