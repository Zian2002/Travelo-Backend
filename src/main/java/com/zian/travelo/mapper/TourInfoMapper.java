package com.zian.travelo.mapper;

import com.zian.travelo.entity.Image;
import com.zian.travelo.entity.Location;
import com.zian.travelo.entity.TourInfo;
import com.zian.travelo.model.dto.TourInfoDTO;
import com.zian.travelo.model.response.TourInfoResponse;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class TourInfoMapper {
    public static TourInfoDTO tourInfoToTourDTO(TourInfo tourInfo){
        return TourInfoDTO.builder()
                .id(tourInfo.getId())
                .name(tourInfo.getName())
                .description(tourInfo.getDescription())
                .itinerary(tourInfo.getItinerary())
                .createdAt(tourInfo.getCreatedAt())
                .location(LocationMapper.locationToLocationResponse(tourInfo.getLocation()))
                .images(tourInfo.getImages())
                .build();
    }

    public static TourInfoResponse tourInfoToTourInfoResponse(TourInfo tourInfo){
        return TourInfoResponse.builder()
                .id(tourInfo.getId())
                .name(tourInfo.getName())
                .description(tourInfo.getDescription())
                .itinerary(tourInfo.getItinerary())
                .createdAt(tourInfo.getCreatedAt())
                .location(LocationMapper.locationToLocationResponse(tourInfo.getLocation()))
                .build();
    }
}
