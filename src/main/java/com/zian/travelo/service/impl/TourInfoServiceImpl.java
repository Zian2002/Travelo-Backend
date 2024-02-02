package com.zian.travelo.service.impl;

import com.zian.travelo.entity.Location;
import com.zian.travelo.entity.Tour;
import com.zian.travelo.entity.TourInfo;
import com.zian.travelo.exception.BadRequestException;
import com.zian.travelo.exception.NotFoundException;
import com.zian.travelo.mapper.TourInfoMapper;
import com.zian.travelo.model.dto.TourInfoDTO;
import com.zian.travelo.model.request.TourInfoRequest;
import com.zian.travelo.repository.LocationRepository;
import com.zian.travelo.repository.TourInfoRepository;
import com.zian.travelo.service.LocationService;
import com.zian.travelo.service.TourInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TourInfoServiceImpl implements TourInfoService {

    private final TourInfoRepository tourInfoRepository;
    private final LocationService locationService;

    @Override
    public List<TourInfoDTO> getAll() {
        return tourInfoRepository.findAllByStatusIsTrue().stream()
                .map(TourInfoMapper::tourInfoToTourDTO).toList();
    }

    @Override
    public TourInfoDTO getById(Long id) {
        TourInfo tourInfo = getTourInfoById(id);
        return TourInfoMapper.tourInfoToTourDTO(tourInfo);
    }

    @Override
    public void add(TourInfoRequest request) {

        Location location = locationService.getLocationById(request.getLocationId());

        TourInfo tourInfo = TourInfo.builder()
                .name(request.getName())
                .location(location)
                .description(request.getDescription())
                .itinerary(request.getItinerary())
                .createdAt(LocalDate.now())
                .status(true)
                .build();

        tourInfoRepository.save(tourInfo);
    }

    @Override
    public void update(Long id, TourInfoRequest request) {
        TourInfo tourInfo = getTourInfoById(id);

        Location location = null;
        if (request.getLocationId() != null) {
            location = locationService.getLocationById(request.getLocationId());
        }
        else{
            location = tourInfo.getLocation();
        }

        tourInfo = TourInfo.builder()
                .id(tourInfo.getId())
                .name(request.getName() != null ? request.getName() : tourInfo.getName())
                .location(location)
                .description(request.getDescription() != null ? request.getDescription() : tourInfo.getDescription())
                .itinerary(request.getItinerary() != null ? request.getItinerary() : tourInfo.getItinerary())
                .createdAt(tourInfo.getCreatedAt())
                .status(tourInfo.getStatus())
                .build();
        tourInfoRepository.save(tourInfo);

    }

    @Override
    public void delete(Long id) {
        TourInfo tourInfo = getTourInfoById(id);
        tourInfo.setStatus(false);
        tourInfoRepository.save(tourInfo);
    }

    @Override
    public TourInfo getTourInfoById(Long id){
        return tourInfoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("TourInfo is not found"));
    }

}
