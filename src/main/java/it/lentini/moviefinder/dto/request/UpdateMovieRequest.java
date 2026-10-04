package it.lentini.moviefinder.dto.request;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public record UpdateMovieRequest(
        @Size(max = 255, message = "Title must not exceed 255 characters")
        String title,

        String description,

        Date releaseDate,

        @Positive(message = "Duration must be positive")
        Integer duration,

        @Size(max = 20, message = "Language must not exceed 20 characters")
        String language,

        @Size(max = 100, message = "Director name must not exceed 100 characters")
        String director,

        List<Long> genreIds
) {}
