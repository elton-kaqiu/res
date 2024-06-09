package com.eltonkaqiu.resbackend.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 3, max = 50, message = "First name should be greater than 3 characters and less or equal to 50!")
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 50, message = "Last name should be greater than 3 characters and less or equal to 50!")
    @Column(nullable = false)
    private String lastName;

    @Pattern(regexp = "\\+?\\d{6,14}", message = "Phone number should contain only digits and may include a leading '+'")
    @Size(min = 7, max = 15, message = "Phone number should be between 7 and 15 characters long")
    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Email(message = "Email should be in the format example@example.com")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getUsername() {
        return email;
    }

}
