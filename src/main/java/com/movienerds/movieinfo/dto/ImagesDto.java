package com.movienerds.movieinfo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
@Getter @Setter
public class ImagesDto {

    private Integer id;
    private ArrayList<ImageDetailDto> logos;
    private ArrayList<ImageDetailDto> backdrops;
    private ArrayList<ImageDetailDto> posters;

    @Builder
    public ImagesDto(Integer id, ArrayList<ImageDetailDto> backdrops, ArrayList<ImageDetailDto> posters) {
        this.id = id;
        this.backdrops = backdrops;
        this.posters = posters;
    }
}
