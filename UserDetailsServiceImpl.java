package com.wipro.auth.service;

import com.wipro.auth.entity.AppUser;
import com.wipro.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UserDetailsServiceImpl
 *
 * Spring Security calls loadUserByUsername() when someone tries to log in.
 * We fetch the user from MySQL and wrap them in a UserDetails object.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found: " + username));

        // Convert "ROLE_USER,ROLE_ADMIN" → list of GrantedAuthority
        List<SimpleGrantedAuthority> authorities = Arrays
                .stream(user.getRoles().split(","))
                .map(String::trim)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
