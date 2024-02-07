package com.zian.travelo.repository;

import com.zian.travelo.entity.TourInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourInfoRepository extends JpaRepository<TourInfo, Long> {
    List<TourInfo> findAllByStatusIsTrue();
}
