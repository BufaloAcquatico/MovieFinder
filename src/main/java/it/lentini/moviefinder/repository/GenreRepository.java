package it.lentini.moviefinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import it.lentini.moviefinder.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
