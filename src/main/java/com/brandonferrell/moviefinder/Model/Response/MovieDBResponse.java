package com.brandonferrell.moviefinder.Model.Response;

import lombok.Data;

import java.util.List;

@Data
public class MovieDBResponse {
    private String title;
    private int id;
    private double popularity;
    private double vote_average;
    private String release_date;
    private List<Language> spoken_languages;
    private List<Genre> genres;

    @Data
    public static class Genre {
        private String id;
        private String name;
    }

    @Data
    public static class Language {
        private String iso_639_1;
        private String name;
    }
}
