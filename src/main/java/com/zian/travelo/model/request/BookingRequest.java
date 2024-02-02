package com.zian.travelo.model.request;

import com.zian.travelo.entity.EStatusBooking;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookingRequest {
    private String email;
    private String name;
    private String phone;
    private String address;
    private Long tourId;
    private Integer numberPerson;
    private EStatusBooking status;
    private String staffEmail;
}
