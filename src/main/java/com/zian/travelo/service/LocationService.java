package com.zian.travelo.service;

import com.zian.travelo.entity.Location;
import com.zian.travelo.model.dto.LocationDTO;
import com.zian.travelo.model.request.LocationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
public interface LocationService {

    List<LocationDTO> getAll();

    LocationDTO getById(Long id);

    Location getLocationById(Long id);

    void add(LocationRequest request);

    void update(Long id, LocationRequest request);

    void delete(Long id);

}
