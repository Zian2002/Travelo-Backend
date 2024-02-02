package com.zian.travelo.service.impl;

import com.zian.travelo.entity.Tour;
import com.zian.travelo.entity.TourInfo;
import com.zian.travelo.exception.NotFoundException;
import com.zian.travelo.mapper.TourMapper;
import com.zian.travelo.model.dto.TourDTO;
import com.zian.travelo.model.request.TourRequest;
import com.zian.travelo.repository.TourRepository;
import com.zian.travelo.service.TourInfoService;
import com.zian.travelo.service.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
    private final TourInfoService tourInfoService;
    @Override
    public List<TourDTO> getAll() {
        List<Tour> list = tourRepository.findAllByStatusIsTrue();

        return list.stream()
                .map(TourMapper::tourToTourDTO)
                .toList();
    }

    @Override
    public TourDTO getById(Long id) {
        Tour tour = getTourById(id);
        return TourMapper.tourToTourDTO(tour);
    }

    @Override
    public Tour getTourById(Long id) {
        return tourRepository.findById(id).orElseThrow(() -> new NotFoundException("Tour is not found"));
    }


    @Override
    public void add(TourRequest request) {
        TourInfo tourInfo = null;
        if (request.getTourInfoId() != null){
            tourInfo = tourInfoService.getTourInfoById(request.getTourInfoId());
        }

        Tour tour = Tour.builder()
                .createdAt(LocalDate.now())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .price(request.getPrice())
                .stock(request.getStock())
                .status(true)
                .tourInfo(tourInfo)
                .build();
        tourRepository.save(tour);
    }

    @Override
    public void update(Long id, TourRequest request) {

        Tour tour = getTourById(id);

        TourInfo tourInfo = null;
        if (request.getTourInfoId() != null){
            tourInfo = tourInfoService.getTourInfoById(request.getTourInfoId());
        }else{
            tourInfo = tour.getTourInfo();
        }

        tour = Tour.builder()
                .id(tour.getId())
                .createdAt(tourInfo.getCreatedAt())
                .startDate(request.getStartDate() != null ? request.getStartDate() : tour.getStartDate())
                .endDate(request.getEndDate() != null ? request.getEndDate() : tour.getEndDate())
                .price(request.getPrice() != null ? request.getPrice() : tour.getPrice())
                .stock(request.getStock() != null ? request.getStock() : tour.getStock())
                .status(tour.getStatus())
                .tourInfo(tourInfo)
                .build();
        tourRepository.save(tour);

    }

    @Override
    public void delete(Long id) {
        Tour tour = getTourById(id);
        tour.setStatus(false);
        tourRepository.save(tour);
    }
}
