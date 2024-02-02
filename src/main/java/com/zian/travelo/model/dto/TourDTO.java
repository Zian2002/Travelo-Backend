package com.zian.travelo.model.dto;

import com.zian.travelo.model.response.TourInfoResponse;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TourDTO {
    private Long id;
    private LocalDate createdAt;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double price;
    private Integer stock;
    private TourInfoDTO tourInfo;

}
