package com.movienerds.movieinfo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class ImageDetailDto {

    private Integer aspect_ratio;
    private String file_path;
    private Integer height;
    private String iso_639_1;
    private Integer vote_average;
    private Integer vote_count;
    private Integer width;
}
