package com.zian.travelo.model.response;

import lombok.*;

import java.time.LocalDate;
import java.util.Map;

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
    private Map<String, String> itinerary;
    private LocalDate createdAt;
    private LocationResponse location;
}
