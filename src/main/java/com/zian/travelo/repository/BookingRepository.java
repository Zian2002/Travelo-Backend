package com.zian.travelo.repository;

import com.zian.travelo.entity.Booking;
import com.zian.travelo.entity.Customer;
import com.zian.travelo.entity.EStatusBooking;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByCustomerEmail(String email);

    List<Booking> findAllByStatus(EStatusBooking status);


}
