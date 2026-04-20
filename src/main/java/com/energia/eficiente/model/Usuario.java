package com.energia.eficiente.model;

import jakarta.persistence.*;
import org.springframework.aot.generate.GeneratedTypeReference;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "USUARIO")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "USER_ROLES",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    )
    @Column(name = "ROLE")
    private Set<Cargo> roles;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Cargo> getRoles() {
        return roles;
    }

    public void setRoles(Set<Cargo> roles) {
        this.roles = roles;
    }
}
