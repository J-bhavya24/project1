package com.wipro.auth.controller;

import com.wipro.auth.dto.AuthResponse;
import com.wipro.auth.dto.LoginRequest;
import com.wipro.auth.dto.RegisterRequest;
import com.wipro.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * AuthController
 *
 * Exposed endpoints:
 *
 *  POST /auth/register         → Register a new user (LOCAL)
 *  POST /auth/login            → Login and get JWT (LOCAL)
 *  GET  /auth/me               → Get current user info (requires JWT)
 *
 * GitHub OAuth2 flow (handled by Spring Security automatically):
 *  GET  /oauth2/authorization/github  → Redirects to GitHub login page
 *  GET  /login/oauth2/code/github     → GitHub calls this after approval (auto-handled)
 *       → OAuth2SuccessHandler fires and returns JWT
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // ── REGISTER ──────────────────────────────────────────────────────────────

    /**
     * Register a new user.
     *
     * Request body:
     * {
     *   "username": "bhavay",
     *   "password": "mypassword",
     *   "role": "USER"       ← or "ADMIN"
     * }
     *
     * Response:
     * {
     *   "token": "eyJhbGciOiJIUzI1NiJ9...",
     *   "username": "bhavay",
     *   "role": "ROLE_USER",
     *   "message": "Registration successful!"
     * }
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    // ── LOGIN ─────────────────────────────────────────────────────────────────

    /**
     * Login with username and password.
     *
     * Request body:
     * {
     *   "username": "bhavay",
     *   "password": "mypassword"
     * }
     *
     * Response:
     * {
     *   "token": "eyJhbGciOiJIUzI1NiJ9...",
     *   "username": "bhavay",
     *   "role": "ROLE_USER",
     *   "message": "Login successful!"
     * }
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    // ── ME (protected) ────────────────────────────────────────────────────────

    /**
     * Returns the currently logged-in user's info.
     * Requires: Authorization: Bearer <token>
     *
     * Works for both LOCAL and GitHub OAuth2 users.
     */
    @GetMapping("/me")
    public ResponseEntity<Map<String, String>> getCurrentUser(Principal principal) {
        Map<String, String> info = new HashMap<>();
        info.put("username", principal.getName());
        info.put("message", "You are authenticated!");
        return ResponseEntity.ok(info);
    }
}
