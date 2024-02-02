package com.zian.travelo.service.impl;

import com.zian.travelo.entity.Staff;
import com.zian.travelo.exception.NotFoundException;
import com.zian.travelo.mapper.StaffMapper;
import com.zian.travelo.model.dto.StaffDTO;
import com.zian.travelo.model.request.StaffRequest;
import com.zian.travelo.repository.StaffRepository;
import com.zian.travelo.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Override
    public List<StaffDTO> getAll() {
        List<Staff> list = staffRepository.findAllByStatusWorkingIsTrue();
        return list.stream()
                .map(StaffMapper::staffToStaffDTO)
                .toList();
    }

    @Override
    public Staff getStaffById(Long id) {
        return staffRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Staff is not found"));
    }

    @Override
    public Staff getStaffByEmail(String email) {
        return staffRepository.findByEmail(email).orElse(null);
    }

    @Override
    public StaffDTO getById(Long id) {
        Staff staff = getStaffById(id);
        return StaffMapper.staffToStaffDTO(staff);
    }

    @Override
    public void add(StaffRequest request) {
        Staff staff = Staff.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .dateOfBirth(request.getDateOfBirth())
                .personalId(request.getPersonalId())
                .statusWorking(true)
                .build();
        staffRepository.save(staff);
    }

    @Override
    public void update(Long id, StaffRequest request) {
        Staff staff = getStaffById(id);
        staff = Staff.builder()
                .id(staff.getId())
                .name(request.getName() != null ? request.getName() : staff.getName())
                .email(staff.getEmail())
                .phone(request.getPhone() != null ? request.getPhone() : staff.getPhone())
                .address(request.getAddress() != null ? request.getAddress() : staff.getAddress())
                .personalId(request.getPersonalId() != null ? request.getPersonalId() : staff.getPersonalId())
                .dateOfBirth(request.getDateOfBirth() != null ? request.getDateOfBirth() : staff.getDateOfBirth())
                .statusWorking(staff.getStatusWorking())
                .build();
        staffRepository.save(staff);

    }

    @Override
    public void delete(Long id) {
        Staff staff = getStaffById(id);
        staff.setStatusWorking(false);
        staffRepository.save(staff);
    }
}
