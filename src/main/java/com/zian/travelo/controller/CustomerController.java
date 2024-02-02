package com.zian.travelo.controller;

import com.zian.travelo.model.dto.CustomerDTO;
import com.zian.travelo.model.request.CustomerRequest;
import com.zian.travelo.model.response.SuccessResponse;
import com.zian.travelo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAll(){
        return ResponseEntity.ok(customerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(customerService.getById(id));
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> add(@RequestBody CustomerRequest request){
        customerService.add(request);
        return ResponseEntity.ok(new SuccessResponse("Create customer successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> update(@PathVariable("id") Long id, @RequestBody CustomerRequest request){
        customerService.update(id, request);
        return ResponseEntity.ok(new SuccessResponse("Update customer successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> delete(@PathVariable("id") Long id){
        customerService.delete(id);
        return ResponseEntity.ok(new SuccessResponse("Delete customer successfully"));
    }

}
