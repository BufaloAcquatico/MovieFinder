package it.lentini.moviefinder.dto.mapper;

import it.lentini.moviefinder.domain.Movie;
import it.lentini.moviefinder.dto.request.CreateMovieRequest;
import it.lentini.moviefinder.dto.request.UpdateMovieRequest;
import it.lentini.moviefinder.dto.response.MovieResponse;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = GenreMapper.class)
public interface MovieMapper {

    MovieResponse toResponse(Movie entity);

    Movie toEntity(CreateMovieRequest request);

    void updateEntity(
            UpdateMovieRequest request,
            @MappingTarget Movie entity
    );
}
