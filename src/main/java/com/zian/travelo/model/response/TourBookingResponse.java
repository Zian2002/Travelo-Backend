package com.zian.travelo.model.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TourBookingResponse {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private TourInfoResponse tourInfo;
    private Double price;
}
