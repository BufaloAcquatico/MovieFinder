package it.lentini.moviefinder.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record UpdateReviewRequest(
    @NotNull
    @PositiveOrZero
    Short rating,
    
    @NotNull
    @Size(min = 1, max = 255)
    String comment
) {}