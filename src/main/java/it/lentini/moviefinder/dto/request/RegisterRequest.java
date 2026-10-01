package it.lentini.moviefinder.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(
        @NotBlank
        String email,
        @Email
        String password
) {
}
