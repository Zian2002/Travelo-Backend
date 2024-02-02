package com.zian.travelo.repository;

import com.zian.travelo.entity.TourInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourInfoRepository extends JpaRepository<TourInfo, Long> {
    List<TourInfo> findAllByStatusIsTrue();
}
