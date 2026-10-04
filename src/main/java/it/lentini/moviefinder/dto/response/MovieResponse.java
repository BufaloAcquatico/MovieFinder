package it.lentini.moviefinder.dto.response;

import java.util.Date;
import java.util.List;

public record MovieResponse(
        Long id,
        String title,
        String description,
        Date releaseDate,
        Integer duration,
        String language,
        String director,
        Short rating,
        List<GenreResponse> genres,
        List<ReviewResponse> reviews
) {}
