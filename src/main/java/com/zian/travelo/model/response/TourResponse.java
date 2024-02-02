package com.zian.travelo.model.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TourResponse {
    private Long id;
    private LocalDate createdAt;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double price;
    private Integer stock;
    private TourInfoResponse tourInfo;

}
