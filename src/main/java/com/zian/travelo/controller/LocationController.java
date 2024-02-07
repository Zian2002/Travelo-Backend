package com.zian.travelo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zian.travelo.entity.Location;
import com.zian.travelo.exception.BadRequestException;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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
                                                  @RequestParam("data") String data,
                                                  @RequestParam("images") List<MultipartFile> images) throws JsonProcessingException {

        System.out.println("size" + images.size());
        images.forEach(System.out::println);

        ObjectMapper mapper = new ObjectMapper();
        LocationRequest request = mapper.readValue(data, LocationRequest.class);
        locationService.update(id, request, images);
        return ResponseEntity.ok(new SuccessResponse("Update location successfully"));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> add(@RequestParam("data") String data,
                                               @RequestParam("images")List<MultipartFile> images) throws IOException {
        if (data == null)
            throw new BadRequestException("please fill full info");
        ObjectMapper mapper = new ObjectMapper();
        LocationRequest request = mapper.readValue(data, LocationRequest.class);

        locationService.add(request, images);
        return ResponseEntity.ok(new SuccessResponse("Create location successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> delete(@PathVariable("id") Long id){
        locationService.delete(id);
        return ResponseEntity.ok(new SuccessResponse("Delete location successfully"));
    }


}
