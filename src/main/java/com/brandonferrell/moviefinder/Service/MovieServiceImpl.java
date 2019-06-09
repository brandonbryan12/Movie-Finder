package com.brandonferrell.moviefinder.Service;

import com.brandonferrell.moviefinder.Client.MovieClient;
import com.brandonferrell.moviefinder.Model.Genre;
import com.brandonferrell.moviefinder.Model.Language;
import com.brandonferrell.moviefinder.Model.Movie;
import com.brandonferrell.moviefinder.Model.Response.MovieDBMultiResponse;
import com.brandonferrell.moviefinder.Model.Response.MovieDBResponse;
import com.brandonferrell.moviefinder.Repository.MovieInsertRepository;
import com.brandonferrell.moviefinder.Repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieClient movieClient;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieInsertRepository movieInsertRepository;

    @Override
    public Movie getMovieById(int id) {

        // Get movie from 3rd party
        MovieDBResponse movieDBResponse = movieClient.getMovieById(id).getBody();
        Movie movie = translateMovie(movieDBResponse);

        // Check if in DB
        Movie movieDb = movieRepository.getMovieById(id);

        // If not in DB, save in DB
        if(movieDb == null) movieInsertRepository.insertMovie(movie);

        log.info("Retrieved movie: {}", movie.toString());

        return movie;
    }

    @Override
    public List<Movie> getMoviesByQuery(String query) {
        MovieDBMultiResponse movieDBMultiResponse = movieClient.getMoviesByQuery(query).getBody();
        List<Movie> movies = new ArrayList<>();

        for(MovieDBResponse movieDBResponse : movieDBMultiResponse.getResults()) {
            MovieDBResponse movieDBResponseFull = movieClient.getMovieById(movieDBResponse.getId()).getBody();
            Movie translated = translateMovie(movieDBResponseFull);
            movies.add(translated);
        }

        return movies;
    }

    @Override
    public Movie translateMovie(MovieDBResponse movieDBResponse) {
        // Genres
        List<Genre> genres = new ArrayList<>();
        if(movieDBResponse.getGenres() != null) {
            for(MovieDBResponse.Genre genre : movieDBResponse.getGenres()) {
                genres.add(new Genre(genre.getName()));
            }
        }

        // Languages
        List<Language> languages = new ArrayList<>();
        if(movieDBResponse.getSpoken_languages() != null) {
            for(MovieDBResponse.Language language : movieDBResponse.getSpoken_languages()) {
                languages.add(new Language(language.getIso_639_1()));
            }
        }

        return Movie.builder().
                id(movieDBResponse.getId()).
                name(movieDBResponse.getTitle()).
                rating(movieDBResponse.getVote_average()).
                year(movieDBResponse.getRelease_date()).
                genres(genres).
                languages(languages).
                build();
    }
}
