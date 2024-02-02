package com.zian.travelo.service;

import com.zian.travelo.entity.Booking;
import com.zian.travelo.model.dto.BookingDTO;
import com.zian.travelo.model.request.BookingRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookingService {

    Page<BookingDTO> getAll(int page, int size);

    BookingDTO getById(Long id);

    Booking getBookingById(Long id);

    List<BookingDTO> getByCustomerEmail(String email);

    void add(BookingRequest request);

    void updateStatus(Long id, BookingRequest request);

}
