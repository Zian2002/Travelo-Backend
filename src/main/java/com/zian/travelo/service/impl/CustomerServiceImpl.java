package com.zian.travelo.service.impl;

import com.zian.travelo.entity.Customer;
import com.zian.travelo.exception.NotFoundException;
import com.zian.travelo.mapper.CustomerMapper;
import com.zian.travelo.model.dto.CustomerDTO;
import com.zian.travelo.model.request.CustomerRequest;
import com.zian.travelo.repository.CustomerRepository;
import com.zian.travelo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerDTO> getAll() {
        List<Customer> list = customerRepository.findAllByStatusIsTrue();
        return list.stream()
                .map(CustomerMapper::customerToCustomerDTO)
                .toList();
    }

    @Override
    public CustomerDTO getById(Long id) {
        Customer customer = getCustomerById(id);
        return CustomerMapper.customerToCustomerDTO(customer);
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer is not found"));
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email).orElse(null);
    }


    @Override
    public void add(CustomerRequest request) {
        Customer customer = Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .dateOfBirth(request.getDateOfBirth())
                .status(true)
                .build();
        customerRepository.save(customer);
    }

    @Override
    public void update(Long id, CustomerRequest request) {
        Customer customer = getCustomerById(id);
        customer = Customer.builder()
                .id(customer.getId())
                .name(request.getName() != null ? request.getName() : customer.getName())
                .email(customer.getEmail())
                .phone(request.getPhone() != null ? request.getPhone() : customer.getPhone())
                .address(request.getAddress() != null ? request.getAddress() : customer.getAddress())
                .dateOfBirth(request.getDateOfBirth() != null ? request.getDateOfBirth() : customer.getDateOfBirth())
                .status(customer.getStatus())
                .build();
        customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        Customer customer = getCustomerById(id);
        customer.setStatus(false);
        customerRepository.save(customer);
    }
}
