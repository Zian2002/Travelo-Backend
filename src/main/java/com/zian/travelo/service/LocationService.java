package com.zian.travelo.service;

import com.zian.travelo.entity.Location;
import com.zian.travelo.model.dto.LocationDTO;
import com.zian.travelo.model.request.LocationRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
public interface LocationService {

    List<LocationDTO> getAll();

    LocationDTO getById(Long id);

    Location getLocationById(Long id);

    void add(LocationRequest request, List<MultipartFile>images) throws IOException;

    void update(Long id, LocationRequest request, List<MultipartFile>images);

    void delete(Long id);

}
