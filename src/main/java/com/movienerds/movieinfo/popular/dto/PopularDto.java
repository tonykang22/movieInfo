package com.movienerds.movieinfo.popular.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;

@ToString
@NoArgsConstructor
@Getter @Setter
public class PopularDto {

    private Integer page;
    private ArrayList<ResultsDto> results;
    @JsonIgnore
    private Integer total_results;
    private Integer total_pages;

    @Builder
    public PopularDto(Integer page, ArrayList<ResultsDto> results, Integer total_results, Integer total_pages) {
        this.page = page;
        this.results = results;
        this.total_results = total_results;
        this.total_pages = total_pages;
    }

}
