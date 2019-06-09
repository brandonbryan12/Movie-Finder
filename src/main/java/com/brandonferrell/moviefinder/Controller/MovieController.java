package com.brandonferrell.moviefinder.Controller;

import com.brandonferrell.moviefinder.Model.Movie;
import com.brandonferrell.moviefinder.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping("/{id}")
    public Movie getById(@PathVariable int id) {
        return movieService.getMovieById(id);
    }

    @RequestMapping("/search")
    public List<Movie> getByQuery(@RequestParam(value="query") String query) {
        return movieService.getMoviesByQuery(query);
    }
}
