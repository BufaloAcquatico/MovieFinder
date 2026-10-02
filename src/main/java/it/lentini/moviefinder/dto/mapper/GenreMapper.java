package it.lentini.moviefinder.dto.mapper;

import it.lentini.moviefinder.domain.Genre;
import it.lentini.moviefinder.dto.request.CreateGenreRequest;
import it.lentini.moviefinder.dto.request.UpdateGenreRequest;
import it.lentini.moviefinder.dto.response.GenreResponse;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreResponse toResponse(Genre entity);

    Genre toEntity(CreateGenreRequest request);

    void updateEntity(
            UpdateGenreRequest request,
            @MappingTarget Genre entity
    );
}
