package com.zian.travelo.controller;

import com.zian.travelo.model.dto.TourInfoDTO;
import com.zian.travelo.model.request.TourInfoRequest;
import com.zian.travelo.model.response.SuccessResponse;
import com.zian.travelo.service.TourInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<SuccessResponse> create(@RequestBody TourInfoRequest request){
        tourInfoService.add(request);
        return ResponseEntity.ok(new SuccessResponse("Create tour info successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> update(@PathVariable("id") Long id, @RequestBody TourInfoRequest request){
        tourInfoService.update(id, request);
        return ResponseEntity.ok(new SuccessResponse("Update tour info successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> delete(@PathVariable("id") Long id){
        tourInfoService.delete(id);
        return ResponseEntity.ok(new SuccessResponse("Delete tour info successfully"));
    }

}
