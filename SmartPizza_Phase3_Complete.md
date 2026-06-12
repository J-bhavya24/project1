# SmartPizza AI - Phase 3 Complete
## JWT Security + Authentication Configuration

Package: com.wipro.pizzaapp

---

# SECURITY PACKAGE

Create:

security
├── JwtService.java
├── JwtAuthFilter.java
├── CustomUserDetailsService.java

config
├── SecurityConfig.java

---

# JwtService.java

```java
package com.wipro.pizzaapp.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET =
            "smartpizzajwtsecretkeysmartpizzajwtsecretkey";

    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                    new Date(System.currentTimeMillis()
                    + 1000*60*60*24))
                .signWith(getKey(),
                        SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isTokenValid(String token) {

        try {
            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token);

            return true;

        } catch(Exception e) {
            return false;
        }
    }
}
```

---

# CustomUserDetailsService.java

```java
package com.wipro.pizzaapp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.wipro.pizzaapp.entity.User;
import com.wipro.pizzaapp.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService
        implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(
            String email)
            throws UsernameNotFoundException {

        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User Not Found"));

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
```

---

# JwtAuthFilter.java

```java
package com.wipro.pizzaapp.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.
UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.
SecurityContextHolder;

import org.springframework.security.core.userdetails.
UserDetails;

import org.springframework.security.web.authentication.
WebAuthenticationDetailsSource;

import org.springframework.stereotype.Component;

import org.springframework.web.filter.
OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter
        extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final CustomUserDetailsService
            userDetailsService;

    @Override
    protected void doFilterInternal(

            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)

            throws ServletException, IOException {

        final String authHeader =
                request.getHeader("Authorization");

        if(authHeader == null ||
                !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request,response);
            return;
        }

        String token =
                authHeader.substring(7);

        String email =
                jwtService.extractUsername(token);

        if(email != null &&
                SecurityContextHolder
                        .getContext()
                        .getAuthentication() == null) {

            UserDetails userDetails =
                    userDetailsService
                            .loadUserByUsername(email);

            if(jwtService.isTokenValid(token)) {

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request));

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request,response);
    }
}
```

---

# SecurityConfig.java

```java
package com.wipro.pizzaapp.config;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.
AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.
AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.
HttpSecurity;

import org.springframework.security.config.http.
SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.
BCryptPasswordEncoder;

import org.springframework.security.crypto.password.
PasswordEncoder;

import org.springframework.security.web.
SecurityFilterChain;

import org.springframework.security.web.authentication.
UsernamePasswordAuthenticationFilter;

import com.wipro.pizzaapp.security.JwtAuthFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config)
            throws Exception {

        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(
                        session -> session
                                .sessionCreationPolicy(
                                        SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/auth/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated())

                .addFilterBefore(
                        jwtAuthFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
```

---

# AUTH FLOW

Register User
↓
Password Encrypted
↓
Stored In MySQL
↓
Login
↓
JWT Generated
↓
Swagger Authorization
↓
Protected APIs Accessible

---

# Phase 3 Completion Checklist

1. Create security package
2. Add JwtService
3. Add JwtAuthFilter
4. Add CustomUserDetailsService
5. Create SecurityConfig
6. Start Project
7. Verify No Bean Errors

NEXT PHASE:
UserService
AuthService
PizzaService
CartService
OrderService
PaymentService
DeliveryService
RecommendationService
AdminService
