package com.zian.travelo.service;

import com.zian.travelo.entity.Customer;
import com.zian.travelo.model.dto.CustomerDTO;
import com.zian.travelo.model.request.CustomerRequest;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAll();

    CustomerDTO getById(Long id);

    Customer getCustomerById(Long id);

    Customer getCustomerByEmail(String email);

    void add(CustomerRequest request);

    void update(Long id, CustomerRequest request);

    void delete(Long id);
}
