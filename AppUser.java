package com.wipro.auth.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;        // BCrypt hashed | "OAUTH2_USER" for GitHub

    @Column(nullable = false)
    private String roles;           // "ROLE_USER" or "ROLE_ADMIN"

    @Column(nullable = false)
    private String provider;        // "LOCAL" or "GITHUB"
}
