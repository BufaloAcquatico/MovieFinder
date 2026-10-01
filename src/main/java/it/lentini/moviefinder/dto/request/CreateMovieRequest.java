package it.lentini.moviefinder.dto.request;

import java.sql.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CreateMovieRequest(
    @NotEmpty String title,
    String description,
    Date releaseDate,
    Integer duration,
    @NotEmpty @Size(max = 20) String language,
    @NotEmpty @Size(max = 100) String director,
    @Size(max = 3) Short rating
) {}