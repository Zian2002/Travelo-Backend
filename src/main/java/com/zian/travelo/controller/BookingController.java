package com.zian.travelo.controller;

import com.zian.travelo.model.dto.BookingDTO;
import com.zian.travelo.model.request.BookingRequest;
import com.zian.travelo.model.response.SuccessResponse;
import com.zian.travelo.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<Page<BookingDTO>> getPage(@RequestParam("page") int page){
        Page<BookingDTO> pages = bookingService.getAll(page,20);
        System.out.println(Optional.ofNullable(pages));
        return ResponseEntity.of(Optional.ofNullable(pages));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(bookingService.getById(id));
    }

    @GetMapping("/email")
    public ResponseEntity<List<BookingDTO>> getByEmail(@RequestBody BookingRequest request){
        return ResponseEntity.ok(bookingService.getByCustomerEmail(request.getEmail()));
    }

    @PostMapping("/useradd")
    public ResponseEntity<SuccessResponse> addByCustomer(@RequestBody BookingRequest request){
        bookingService.add(request);
        return ResponseEntity.ok(new SuccessResponse("Create booking successfully"));
    }


    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> updateStatus(@PathVariable("id") Long id,
                                                        @RequestBody BookingRequest request){
        bookingService.updateStatus(id, request);
        return ResponseEntity.ok(new SuccessResponse("Update status booking successfully"));

    }


}
