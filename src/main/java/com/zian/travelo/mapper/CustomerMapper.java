package com.zian.travelo.mapper;

import com.zian.travelo.entity.Customer;
import com.zian.travelo.model.dto.CustomerDTO;
import com.zian.travelo.model.response.CustomerResponse;

public class CustomerMapper {


    public static CustomerDTO customerToCustomerDTO(Customer customer){
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .dateOfBirth(customer.getDateOfBirth())
                .build();
    }

    public static CustomerResponse customerToCustomerResponse(Customer customer){
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .build();
    }
}
