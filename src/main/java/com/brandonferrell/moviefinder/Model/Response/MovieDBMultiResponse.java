package com.brandonferrell.moviefinder.Model.Response;

import lombok.Data;

import java.util.List;

@Data
public class MovieDBMultiResponse {
    List<MovieDBResponse> results;
}
