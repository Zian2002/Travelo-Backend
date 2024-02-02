package com.zian.travelo.service.impl;

import com.zian.travelo.entity.Location;
import com.zian.travelo.exception.NotFoundException;
import com.zian.travelo.mapper.LocationMapper;
import com.zian.travelo.model.dto.LocationDTO;
import com.zian.travelo.model.request.LocationRequest;
import com.zian.travelo.repository.LocationRepository;
import com.zian.travelo.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository repository;

    @Override
    public List<LocationDTO> getAll() {


        return repository.findAllByStatusIsTrue()
                .stream()
                .map(LocationMapper::locationToLocationDTO)
                .toList();
    }

    @Override
    public LocationDTO getById(Long id) {
        Location location = getLocationById(id);
        return LocationMapper.locationToLocationDTO(location);
    }

    @Override
    public void add(LocationRequest request) {
        Location location = Location.builder()
                .name(request.getName())
                .description(request.getDescription())
                .province(request.getProvince())
                .status(true)
                .build();
        repository.save(location);
    }

    @Override
    public void update(Long id, LocationRequest request) {
        Location location = getLocationById(id);

        Location newLocation = Location.builder()
                .id(location.getId())
                .name(request.getName() != null ? request.getName() : location.getName())
                .description(request.getDescription() != null ? request.getDescription() : location.getDescription())
                .province(request.getProvince() != null ? request.getProvince(): location.getProvince())
                .status(location.getStatus())
                .images(location.getImages())
                .build();

        repository.save(newLocation);
    }

    @Override
    public void delete(Long id) {
        Location location = getLocationById(id);
        location.setStatus(false);
        repository.save(location);
    }

    @Override
    public Location getLocationById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Location is not found"));
    }
}
