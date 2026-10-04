package it.lentini.moviefinder.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import it.lentini.moviefinder.domain.Movie;
import it.lentini.moviefinder.dto.request.CreateMovieRequest;
import it.lentini.moviefinder.dto.request.UpdateMovieRequest;
import it.lentini.moviefinder.dto.response.MovieResponse;
import it.lentini.moviefinder.dto.response.MovieSummaryResponse;

@Mapper(componentModel = "spring", uses = {GenreMapper.class})
public interface MovieMapper {
    MovieResponse toResponse(Movie movie);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "genres", ignore = true) // Handled in service via genre IDs
    @Mapping(target = "reviews", ignore = true)
    Movie toEntity(CreateMovieRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    void updateEntityFromRequest(UpdateMovieRequest request, @MappingTarget Movie movie);

    MovieSummaryResponse toSummaryResponse(Movie movie);
}

