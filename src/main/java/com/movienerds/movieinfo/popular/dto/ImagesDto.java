package com.movienerds.movieinfo.popular.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
@Getter @Setter
public class ImagesDto {

    private Integer id;
    private ArrayList<BackdropsDto> backdrops;
    private ArrayList<PostersDto> posters;

    @Builder
    public ImagesDto(Integer id, ArrayList<BackdropsDto> backdrops, ArrayList<PostersDto> posters) {
        this.id = id;
        this.backdrops = backdrops;
        this.posters = posters;
    }
}
