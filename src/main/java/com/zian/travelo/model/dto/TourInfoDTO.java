package com.zian.travelo.model.dto;

import com.zian.travelo.entity.Image;
import com.zian.travelo.model.response.LocationResponse;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TourInfoDTO {
    private Long id;
    private String name;
    private String description;
    private String itinerary;
    private LocalDate createdAt;
    private LocationResponse location;
    private List<Image> images;
}
