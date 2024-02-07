package com.zian.travelo.service;

import com.zian.travelo.entity.Tour;
import com.zian.travelo.model.dto.TourDTO;
import com.zian.travelo.model.request.TourRequest;

import java.util.List;
public interface TourService {
    List<TourDTO> getAll();

    TourDTO getById(Long id);

    Tour getTourById(Long id);

    void add(TourRequest request);

    void update(Long id, TourRequest request);

    void delete(Long id);

}
