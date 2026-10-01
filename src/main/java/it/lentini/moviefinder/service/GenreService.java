package it.lentini.moviefinder.service;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import it.lentini.moviefinder.dto.request.CreateGenreRequest;
import it.lentini.moviefinder.dto.request.UpdateGenreRequest;
import it.lentini.moviefinder.repository.GenreRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor 
public class GenreService {
    private final GenreRepository genreRepository;

    public @Nullable Object findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    public @Nullable Object find(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    public @Nullable Object create(CreateGenreRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    public @Nullable Object update(Long id, UpdateGenreRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
