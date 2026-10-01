package it.lentini.moviefinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.lentini.moviefinder.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}