package it.lentini.moviefinder.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CreateGenreRequest(
    @NotEmpty @Size(max = 50) String name
) {}