package com.zian.travelo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zian.travelo.exception.BadRequestException;
import com.zian.travelo.model.dto.TourInfoDTO;
import com.zian.travelo.model.request.TourInfoRequest;
import com.zian.travelo.model.response.SuccessResponse;
import com.zian.travelo.service.TourInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tourinfo")
public class TourInfoController {
    private final TourInfoService tourInfoService;

    @GetMapping
    public ResponseEntity<List<TourInfoDTO>> getAll(){
        return ResponseEntity.ok(tourInfoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourInfoDTO> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(tourInfoService.getById(id));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> create(@RequestParam("data") String data,
                                                  @RequestParam("images")List<MultipartFile> images) throws JsonProcessingException {
        if (data == null)
            throw new BadRequestException("please fill full info");
        ObjectMapper mapper = new ObjectMapper();
        TourInfoRequest request = mapper.readValue(data, TourInfoRequest.class);
        tourInfoService.add(request, images);
        return ResponseEntity.ok(new SuccessResponse("Create tour info successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> update(@PathVariable("id") Long id, @RequestParam("data") String data,
                                                  @RequestParam("images")List<MultipartFile> images) throws JsonProcessingException {
        if (data == null)
            throw new BadRequestException("please fill full info");
        ObjectMapper mapper = new ObjectMapper();
        TourInfoRequest request = mapper.readValue(data, TourInfoRequest.class);
        tourInfoService.update(id, request, images);
        return ResponseEntity.ok(new SuccessResponse("Update tour info successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> delete(@PathVariable("id") Long id){
        tourInfoService.delete(id);
        return ResponseEntity.ok(new SuccessResponse("Delete tour info successfully"));
    }

}
