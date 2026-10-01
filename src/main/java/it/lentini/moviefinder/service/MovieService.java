package it.lentini.moviefinder.service;

import org.springframework.stereotype.Service;

import it.lentini.moviefinder.repository.MovieRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor 
public class MovieService{
    
    private final MovieRepository movieRepository;
}
