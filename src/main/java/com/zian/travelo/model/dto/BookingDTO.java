package com.zian.travelo.model.dto;

import com.zian.travelo.entity.EStatusBooking;
import com.zian.travelo.model.response.CustomerResponse;
import com.zian.travelo.model.response.StaffResponse;
import com.zian.travelo.model.response.TourBookingResponse;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookingDTO {
    private Long id;
    private String name;
    private String email;
    private int numberPerson;
    private LocalDate createdAt;
    private EStatusBooking status;
    private double totalPrice;
    private CustomerResponse customer;
    private StaffResponse staff;
    private TourBookingResponse tour;

    public double getTotalPrice() {
        return tour.getPrice() * numberPerson;
    }
}
