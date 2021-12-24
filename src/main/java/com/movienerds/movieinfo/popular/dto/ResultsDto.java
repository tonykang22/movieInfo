package com.movienerds.movieinfo.popular.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
@NoArgsConstructor
public class ResultsDto {

    private String poster_path;
    private boolean adult;
    private String overview;
    private String release_date;
    @JsonIgnore
    private ArrayList<Integer> genre_ids;
    private Integer id;
    @JsonIgnore
    private String original_title;
    @JsonIgnore
    private String original_language;
    private String title;
    private String backdrop_path;
    private Integer popularity;
    @JsonIgnore
    private Integer vote_count;
    private boolean video;
    @JsonIgnore
    private Integer vote_average;

}
