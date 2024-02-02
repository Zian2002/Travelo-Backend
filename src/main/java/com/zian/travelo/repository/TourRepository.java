package com.zian.travelo.repository;

import com.zian.travelo.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findAllByStatusIsTrue();
}
