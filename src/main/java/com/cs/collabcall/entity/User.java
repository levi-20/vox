package com.cs.collabcall.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /// only one consecutive . (dot)
    /// only two consecutive \_ (underscore)
    /// must not start with .
    /// minimum length is 5
    /// maximum length is 20
    @NotNull
    @Size(max = 20, message = "username name too long allowed length is 20")
    @Pattern(regexp = "^(?![.-])(?!.*\\.{2})(?!.*_{3})(?!.*-{3})[a-zA-Z0-9._-]{5,20}(?<![._-])$")
    private String username;

    @NotNull
    @Size(max = 100, message = "Name can't be more than 100 characters")
    private String name;

    @Email(message = "Invalid email format")
    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Size(min = 5, message = "Too small password, allowed length 5 - 20")
    private String password;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {

        return this.password;
    }

    @Override
    public String getUsername() {

        return this.username;
    }
}
