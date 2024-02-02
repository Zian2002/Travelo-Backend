package com.zian.travelo.mapper;

import com.zian.travelo.entity.Booking;
import com.zian.travelo.model.dto.BookingDTO;

public class BookingMapper {
    public static BookingDTO bookingToBookingDTO(Booking booking){
        return BookingDTO.builder()
                .id(booking.getId())
                .name(booking.getName())
                .email(booking.getEmail())
                .numberPerson(booking.getNumberPerson())
                .createdAt(booking.getCreatedAt())
                .status(booking.getStatus())
                .totalPrice(booking.getTotalPrice())
                .customer(CustomerMapper.customerToCustomerResponse(booking.getCustomer()))
                .staff(booking.getStaff() != null ? StaffMapper.staffToStaffResponse(booking.getStaff()) : null)
                .tour(TourMapper.tourToTourBookingResponse(booking.getTour()))
                .build();
    }

}
