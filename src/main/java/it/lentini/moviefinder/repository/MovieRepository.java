package it.lentini.moviefinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.lentini.moviefinder.domain.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
