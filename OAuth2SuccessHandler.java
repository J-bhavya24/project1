package com.wipro.auth.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * OAuth2SuccessHandler
 *
 * Spring Security calls this automatically after a successful GitHub OAuth2 login.
 *
 * Flow:
 *   User clicks "Login with GitHub"
 *   → GitHub asks user to authorize your app
 *   → GitHub redirects back to: /login/oauth2/code/github
 *   → Spring Security processes it
 *   → THIS HANDLER runs
 *   → We issue our own JWT and return it as JSON
 *
 * The client can then use the JWT as:
 *   Authorization: Bearer <token>
 */
@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private AuthService authService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // GitHub provides the username under the "login" attribute
        String githubUsername = oAuth2User.getAttribute("login");

        // Issue our own JWT for this GitHub user
        String token = authService.handleOAuth2User(githubUsername);

        // Return JSON response
        Map<String, String> result = new HashMap<>();
        result.put("token", token);
        result.put("username", githubUsername);
        result.put("provider", "GITHUB");
        result.put("message", "GitHub OAuth2 login successful. Use: Authorization: Bearer <token>");

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        new ObjectMapper().writeValue(response.getWriter(), result);
    }
}
