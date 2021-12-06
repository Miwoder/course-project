package com.leverx.govoronok.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "createdAt", nullable = false)
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "approved", nullable = false)
    private Boolean approved = Boolean.FALSE;

    @Column(name = "confirmedByAdmin", nullable = false)
    private Boolean confirmedByAdmin = Boolean.FALSE;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "trader", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<Comment> commentsForThisUser;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<Comment> commentsByThisUser;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<GameObject> gameObjects;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
