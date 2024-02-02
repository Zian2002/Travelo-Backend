package com.zian.travelo.model.dto;

import com.zian.travelo.entity.Image;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LocationDTO {
    private Long id;
    private String name;
    private String province;
    private String description;
    private List<Image> images;
}
