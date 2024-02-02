package com.zian.travelo.model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class StaffDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String personalId;
    private String address;
    private LocalDate dateOfBirth;
}
