package com.zian.travelo.model.response;

import com.zian.travelo.entity.Image;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TourInfoResponse {
    private Long id;
    private String name;
    private String description;
    private String itinerary;
    private LocalDate createdAt;
    private LocationResponse location;
}
