package com.wipro.auth.service;

import com.wipro.auth.dto.AuthResponse;
import com.wipro.auth.dto.LoginRequest;
import com.wipro.auth.dto.RegisterRequest;
import com.wipro.auth.entity.AppUser;
import com.wipro.auth.repository.UserRepository;
import com.wipro.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * AuthService
 *
 * Handles three flows:
 *
 * 1. REGISTER  → validates input, saves user to MySQL, returns JWT
 * 2. LOGIN     → authenticates via Spring Security, returns JWT
 * 3. OAUTH2    → called after GitHub login, auto-creates user if new, returns JWT
 */
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    // ── 1. REGISTER ───────────────────────────────────────────────────────────

    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists: " + request.getUsername());
        }

        // Build role string, e.g. "USER" → "ROLE_USER"
        String role = "ROLE_" + request.getRole().toUpperCase();

        AppUser user = AppUser.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(role)
                .provider("LOCAL")
                .build();

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getUsername(), user.getRoles());

        return new AuthResponse(token, user.getUsername(), user.getRoles(),
                "Registration successful! Use the token to access protected APIs.");
    }

    // ── 2. LOGIN ──────────────────────────────────────────────────────────────

    public AuthResponse login(LoginRequest request) {

        // This throws BadCredentialsException if username/password is wrong
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        AppUser user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(user.getUsername(), user.getRoles());

        return new AuthResponse(token, user.getUsername(), user.getRoles(),
                "Login successful!");
    }

    // ── 3. OAUTH2 (GitHub) ────────────────────────────────────────────────────

    /**
     * Called by OAuth2SuccessHandler after GitHub redirects back.
     * If the GitHub user is new → we auto-register them as ROLE_USER.
     * Either way we issue our own JWT.
     */
    public String handleOAuth2User(String githubUsername) {

        AppUser user = userRepository.findByUsername(githubUsername)
                .orElseGet(() -> {
                    // First time this GitHub user logs in → save them
                    AppUser newUser = AppUser.builder()
                            .username(githubUsername)
                            .password("OAUTH2_USER")   // no real password needed
                            .roles("ROLE_USER")
                            .provider("GITHUB")
                            .build();
                    return userRepository.save(newUser);
                });

        return jwtUtil.generateToken(user.getUsername(), user.getRoles());
    }
}
