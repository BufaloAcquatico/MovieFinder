package it.lentini.moviefinder.service;

import org.springframework.stereotype.Service;

import it.lentini.moviefinder.repository.GenreRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor 
public class GenreService {
    private final GenreRepository genreRepository;

    
}
