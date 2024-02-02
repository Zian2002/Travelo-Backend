package com.zian.travelo.controller;

import com.zian.travelo.entity.Location;
import com.zian.travelo.model.dto.LocationDTO;
import com.zian.travelo.model.request.LocationRequest;
import com.zian.travelo.model.response.SuccessResponse;
import com.zian.travelo.repository.LocationRepository;
import com.zian.travelo.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/location")
public class LocationController {
    private final LocationService locationService;

    @GetMapping
    public ResponseEntity<List<LocationDTO>> getAll(){
        return ResponseEntity.ok(locationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationDTO> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(locationService.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> update(@PathVariable("id") Long id,
             @RequestBody LocationRequest request){
        locationService.update(id, request);
        return ResponseEntity.ok(new SuccessResponse("Update location successfully"));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> add(@RequestBody LocationRequest request){
        locationService.add(request);
        return ResponseEntity.ok(new SuccessResponse("Create location successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> delete(@PathVariable("id") Long id){
        locationService.delete(id);
        return ResponseEntity.ok(new SuccessResponse("Delete location successfully"));
    }


}
