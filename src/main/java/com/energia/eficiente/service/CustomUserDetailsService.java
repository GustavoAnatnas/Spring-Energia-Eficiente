package com.energia.eficiente.service;

import com.energia.eficiente.model.Usuario;
import com.energia.eficiente.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository userRepo;
    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    public CustomUserDetailsService(UsuarioRepository userRepo) {
        this.userRepo = userRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario appUser = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email ));

        var authorities = appUser.getRoles()
                .stream()
                .map(r -> new org.springframework.security.core.authority.SimpleGrantedAuthority(r.name()))
                .collect(Collectors.toSet());
        log.info("Roles carregadas para {}: {}", email, authorities);

        return User.withUsername(appUser.getEmail())
                .password(appUser.getPassword())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
