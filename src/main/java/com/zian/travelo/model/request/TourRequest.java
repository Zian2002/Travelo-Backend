package com.zian.travelo.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TourRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private Double price;
    private Integer stock;
    private Long tourInfoId;
}
