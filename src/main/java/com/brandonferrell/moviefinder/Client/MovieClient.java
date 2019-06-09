package com.brandonferrell.moviefinder.Client;

import com.brandonferrell.moviefinder.Model.Response.MovieDBMultiResponse;
import com.brandonferrell.moviefinder.Model.Response.MovieDBResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "movie", url = "${apiUrl}")
public interface MovieClient {
    @RequestMapping(method = RequestMethod.GET, value = "movie/{id}?api_key=${apiKey}", produces = "application/json")
    ResponseEntity<MovieDBResponse> getMovieById(@PathVariable("id") int id);

    @RequestMapping(method = RequestMethod.GET, value = "search/movie?api_key=${apiKey}&query={query}", produces = "application/json")
    ResponseEntity<MovieDBMultiResponse> getMoviesByQuery(@PathVariable("query") String query);
}
