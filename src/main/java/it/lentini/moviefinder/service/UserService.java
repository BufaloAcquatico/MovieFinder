package it.lentini.moviefinder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.lentini.moviefinder.domain.User;
import it.lentini.moviefinder.repository.UserRepository;
import it.lentini.moviefinder.security.CustomUserDetails;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found")
                );

        return new CustomUserDetails(user);
    }

    public UserDetails findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
    }
}