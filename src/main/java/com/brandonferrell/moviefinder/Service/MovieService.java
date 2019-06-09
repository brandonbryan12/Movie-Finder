package com.brandonferrell.moviefinder.Service;

import com.brandonferrell.moviefinder.Model.Movie;
import com.brandonferrell.moviefinder.Model.Response.MovieDBResponse;

import java.util.List;

public interface MovieService {
    Movie getMovieById(int id);
    List<Movie> getMoviesByQuery(String query);
    Movie translateMovie(MovieDBResponse movieDBResponse);
}
