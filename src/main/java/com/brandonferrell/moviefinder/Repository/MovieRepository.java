package com.brandonferrell.moviefinder.Repository;

import com.brandonferrell.moviefinder.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie getMovieById(final int movieId);
}
