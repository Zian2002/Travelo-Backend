package com.zian.travelo.repository;

import com.zian.travelo.entity.Booking;
import com.zian.travelo.entity.Customer;
import com.zian.travelo.entity.EStatusBooking;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByCustomerEmail(String email);

    List<Booking> findAllByStatus(EStatusBooking status);


}
