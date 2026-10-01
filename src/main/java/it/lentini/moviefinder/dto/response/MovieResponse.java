package it.lentini.moviefinder.dto.response;

import java.sql.Date;
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
    List<Long> genres,
    List<Long> reviews
) {}
