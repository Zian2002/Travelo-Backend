package com.zian.travelo.repository;

import com.zian.travelo.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {

    List<Staff> findAllByStatusWorkingIsTrue();

    Optional<Staff> findByEmail(String email);

}
