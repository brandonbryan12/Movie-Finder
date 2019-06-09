package com.brandonferrell.moviefinder.Model;

import lombok.*;
import lombok.experimental.Wither;

import javax.persistence.*;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Wither
@Entity
@ToString
public class Movie {
    @Id
    @Column(unique = true)
    private int id;

    private String name;

    private String year;

    private double rating;

    @Transient
    private List<Language> languages;

    @Transient
    private List<Genre> genres;
}