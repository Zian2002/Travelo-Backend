package com.zian.travelo.mapper;

import com.zian.travelo.entity.Location;
import com.zian.travelo.model.dto.LocationDTO;
import com.zian.travelo.model.response.LocationResponse;

public class LocationMapper {

    public static LocationDTO locationToLocationDTO(Location location){
        return LocationDTO.builder()
                .id(location.getId())
                .name(location.getName())
                .province(location.getProvince())
                .description(location.getDescription())
                .images(location.getImages())
                .build();
    }

    public static LocationResponse locationToLocationResponse(Location location){
        return LocationResponse.builder()
                .id(location.getId())
                .name(location.getName())
                .province(location.getProvince())
                .description(location.getDescription())
                .build();
    }

}
