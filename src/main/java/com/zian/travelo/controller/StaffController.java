package com.zian.travelo.controller;

import com.zian.travelo.model.dto.StaffDTO;
import com.zian.travelo.model.request.StaffRequest;
import com.zian.travelo.model.response.SuccessResponse;
import com.zian.travelo.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/staff")
public class StaffController {

    private final StaffService staffService;

    @GetMapping
    public ResponseEntity<List<StaffDTO>> getAll(){
        return ResponseEntity.ok(staffService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StaffDTO> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(staffService.getById(id));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> add(@RequestBody StaffRequest request){
        staffService.add(request);
        return ResponseEntity.ok(new SuccessResponse("Create Staff successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> update(@PathVariable("id") Long id, @RequestBody StaffRequest request){
        staffService.update(id, request);
        return ResponseEntity.ok(new SuccessResponse("Update Staff successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> delete(@PathVariable("id") Long id){
        staffService.delete(id);
        return ResponseEntity.ok(new SuccessResponse("Delete Staff successfully"));
    }


}
