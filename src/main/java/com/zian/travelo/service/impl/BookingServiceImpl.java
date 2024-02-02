package com.zian.travelo.service.impl;

import com.zian.travelo.entity.*;
import com.zian.travelo.exception.BadRequestException;
import com.zian.travelo.exception.NotFoundException;
import com.zian.travelo.mapper.BookingMapper;
import com.zian.travelo.model.dto.BookingDTO;
import com.zian.travelo.model.request.BookingRequest;
import com.zian.travelo.model.request.CustomerRequest;
import com.zian.travelo.repository.BookingRepository;
import com.zian.travelo.repository.TourRepository;
import com.zian.travelo.service.BookingService;
import com.zian.travelo.service.CustomerService;
import com.zian.travelo.service.StaffService;
import com.zian.travelo.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final TourRepository tourRepository;
    private final StaffService staffService;
    private final CustomerService customerService;
    private final TourService tourService;

    @Override
    public Page<BookingDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Booking> pages = bookingRepository.findAll(pageable);
        return pages.map(BookingMapper::bookingToBookingDTO);
    }

    @Override
    public BookingDTO getById(Long id) {
        Booking booking = getBookingById(id);
        return BookingMapper.bookingToBookingDTO(booking);
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Booking is not found"));
    }

    @Override
    public List<BookingDTO> getByCustomerEmail(String email) {
        List<Booking> list = bookingRepository.findAllByCustomerEmail(email);
        return list.stream()
                .map(BookingMapper::bookingToBookingDTO)
                .toList();
    }

    @Override
    public void add(BookingRequest request) {
        Map<String, String> infoMessage = new HashMap<String, String>();
        if (request.getTourId() == null){
            infoMessage.put("tour","TourId must be required.");
        }
        if (request.getEmail() == null){
            infoMessage.put("email","Email must be required.");
        }
        if (request.getName() == null){
            infoMessage.put("name","Name must be required.");
        }
        if (request.getPhone() == null){
            infoMessage.put("phone","Phone must be required.");
        }
        if (request.getNumberPerson() == null || request.getNumberPerson() < 1){
            infoMessage.put("numberPerson","Number person must be required anf greater than equal 1.");
        }

        Tour tour = tourService.getTourById(request.getTourId());
        if (request.getNumberPerson() != null && tour.getStock() < request.getNumberPerson()){
            infoMessage.put("numberPerson","Number person can not be greater than stock.");
        }
        if (!infoMessage.isEmpty()){
            throw new BadRequestException("Bad request", infoMessage);
        }

        Customer customer = customerService.getCustomerByEmail(request.getEmail());
        if (customer == null) {
            CustomerRequest customerRequest = CustomerRequest.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .phone(request.getPhone())
                    .build();
            customerService.add(customerRequest);
        }
        customer = customerService.getCustomerByEmail(request.getEmail());

        Staff staff = null;
        if (request.getStaffEmail() != null){
            staff = staffService.getStaffByEmail(request.getStaffEmail());
        }


        Booking booking = Booking.builder()
                .status(request.getStatus() != null ? request.getStatus() : EStatusBooking.NEW)
                .createdAt(LocalDate.now())
                .numberPerson(request.getNumberPerson())
                .name(request.getName())
                .email(request.getEmail())
                .build();
        booking.setTotalPrice(booking.getTotalPrice());
        booking.setCustomer(customer);
        booking.setStaff(staff);
        booking.setTour(tour);
        bookingRepository.save(booking);

        tour.decreaseStock(request.getNumberPerson());
        tourRepository.save(tour);
    }

    @Override
    public void updateStatus(Long id, BookingRequest request) {
        Booking booking = getBookingById(id);
        HashMap<String, String> infoMessage = new HashMap<String, String>();
        if (request.getStatus() == null){
            infoMessage.put("statusBooking", "booking status is required");
            throw new BadRequestException("Bad request", infoMessage);
        }
        else{
            booking.setStatus(request.getStatus());
            bookingRepository.save(booking);
        }
    }
}
