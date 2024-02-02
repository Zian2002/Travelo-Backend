package com.zian.travelo.repository;

import com.zian.travelo.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    List<Staff> findAllByStatusWorkingIsTrue();

    Optional<Staff> findByEmail(String email);

}
