package it.lentini.moviefinder.dto.response;

import java.sql.Timestamp;

public record ReviewResponse(
        Long id,
        Short rating,
        String comment,
        Timestamp createdAt,
        UserSummaryResponse user,
        MovieSummaryResponse movie
) {}
