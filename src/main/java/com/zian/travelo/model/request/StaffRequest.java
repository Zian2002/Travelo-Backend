package com.zian.travelo.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class StaffRequest {
    private String name;
    private String email;
    private String phone;
    private String personalId;
    private String address;
    private LocalDate dateOfBirth;
}
