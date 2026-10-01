package it.lentini.moviefinder.service;

import org.springframework.stereotype.Service;

import it.lentini.moviefinder.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor 
public class ReviewService {
    private final ReviewRepository reviewRepository;
}
