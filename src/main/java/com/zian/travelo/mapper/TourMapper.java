package com.zian.travelo.mapper;

import com.zian.travelo.entity.Tour;
import com.zian.travelo.model.dto.TourDTO;
import com.zian.travelo.model.response.TourBookingResponse;

public class TourMapper {

    public static TourDTO tourToTourDTO(Tour tour){
        return TourDTO.builder()
                .id(tour.getId())
                .createdAt(tour.getCreatedAt())
                .startDate(tour.getStartDate())
                .endDate(tour.getEndDate())
                .price(tour.getPrice())
                .stock(tour.getStock())
                .tourInfo(TourInfoMapper.tourInfoToTourDTO(tour.getTourInfo()))
                .build();
    }

    public static TourBookingResponse tourToTourBookingResponse(Tour tour){
        return TourBookingResponse.builder()
                .startDate(tour.getStartDate())
                .endDate(tour.getEndDate())
                .tourInfo(TourInfoMapper.tourInfoToTourInfoResponse(tour.getTourInfo()))
                .price(tour.getPrice())
                .build();
    }


}
