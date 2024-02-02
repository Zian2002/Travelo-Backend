package com.zian.travelo.mapper;

import com.zian.travelo.entity.Staff;
import com.zian.travelo.model.dto.StaffDTO;
import com.zian.travelo.model.response.StaffResponse;

public class StaffMapper {
    public static StaffDTO staffToStaffDTO(Staff staff){
        return StaffDTO.builder()
                .id(staff.getId())
                .name(staff.getName())
                .email(staff.getEmail())
                .phone(staff.getPhone())
                .address(staff.getAddress())
                .dateOfBirth(staff.getDateOfBirth())
                .personalId(staff.getPersonalId())
                .build();
    }

    public static StaffResponse staffToStaffResponse(Staff staff){
        return StaffResponse.builder()
                .id(staff.getId())
                .name(staff.getName())
                .email(staff.getEmail())
                .phone(staff.getPhone())
                .build();
    }
}
