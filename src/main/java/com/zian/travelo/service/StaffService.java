package com.zian.travelo.service;

import com.zian.travelo.entity.Staff;
import com.zian.travelo.model.dto.StaffDTO;
import com.zian.travelo.model.request.StaffRequest;

import java.util.List;

public interface StaffService {
    List<StaffDTO> getAll();

    Staff getStaffById(Long id);

    Staff getStaffByEmail(String email);

    StaffDTO getById(Long id);

    void add(StaffRequest request);

    void update(Long id, StaffRequest request);

    void delete(Long id);
}
