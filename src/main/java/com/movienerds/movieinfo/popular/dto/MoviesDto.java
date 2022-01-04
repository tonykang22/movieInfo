package com.movienerds.movieinfo.popular.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
@Getter @Setter
public class MoviesDto {

    private Integer page;
    private ArrayList<ResultsDto> results;
    @JsonIgnore
    private ArrayList<String> dates;
    private Integer total_pages;
    @JsonIgnore
    private Integer total_results;

    @Builder
    public MoviesDto(Integer page, ArrayList<ResultsDto> results, ArrayList<String> dates, Integer total_results, Integer total_pages) {
        this.page = page;
        this.results = results;
        this.dates = dates;
        this.total_results = total_results;
        this.total_pages = total_pages;
    }

}
