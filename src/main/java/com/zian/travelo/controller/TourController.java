package com.zian.travelo.controller;

import com.zian.travelo.model.dto.TourDTO;
import com.zian.travelo.model.request.TourRequest;
import com.zian.travelo.model.response.SuccessResponse;
import com.zian.travelo.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tour")
public class TourController {
    private final TourService tourService;

    @GetMapping
    public ResponseEntity<List<TourDTO>> getAll(){
        return ResponseEntity.ok(tourService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourDTO> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(tourService.getById(id));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> add(@RequestBody TourRequest request){
        tourService.add(request);
        return ResponseEntity.ok(new SuccessResponse("Create Tour successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> update(@PathVariable("id") Long id, @RequestBody TourRequest request){
        tourService.update(id, request);
        return ResponseEntity.ok(new SuccessResponse("Update Tour successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> delete(@PathVariable("id") Long id){
        tourService.delete(id);
        return ResponseEntity.ok(new SuccessResponse("Delete Tour successfully"));
    }

}
