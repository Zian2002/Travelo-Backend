package com.zian.travelo.repository;

import com.zian.travelo.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findAllByStatusIsTrue();

}
