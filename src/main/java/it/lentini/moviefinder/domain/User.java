package it.lentini.moviefinder.domain;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name="password", nullable = false, length=50)
    private String password;

    @Column(name="enabled", nullable = false)
    private boolean enabled;

    @Column(name="locked", nullable = false)
    private boolean locked;

    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;
}