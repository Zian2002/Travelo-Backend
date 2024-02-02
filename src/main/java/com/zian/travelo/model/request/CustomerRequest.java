package com.zian.travelo.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CustomerRequest {
    private String name;
    private String email;
    private String phone;
    private String address;
    private LocalDate dateOfBirth;
}
