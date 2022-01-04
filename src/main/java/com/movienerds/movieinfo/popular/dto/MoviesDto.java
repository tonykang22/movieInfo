package com.movienerds.movieinfo.popular.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
@Getter @Setter
public class MoviesDto {

    private boolean adult;
    private String backdrop_path;
    private Object belongs_to_collection;
    private Integer budget;
    private ArrayList<Object> genres;
    private String homepage;
    private Integer id;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private Integer popularity;
    private String poster_path;
    private ArrayList<Object> production_companies;
    private ArrayList<Object> production_countries;
    private String release_date;
    private Integer revenue;
    private Integer runtime;
    private ArrayList<Object> spoken_languages;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private Integer vote_average;
    private Integer vote_count;

}
