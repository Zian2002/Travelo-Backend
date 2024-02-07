package com.zian.travelo.service;

import com.zian.travelo.entity.TourInfo;
import com.zian.travelo.model.dto.TourInfoDTO;
import com.zian.travelo.model.request.TourInfoRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TourInfoService {

    List<TourInfoDTO> getAll();

    TourInfoDTO getById(Long id);

    TourInfo getTourInfoById(Long id);

    void add(TourInfoRequest request, List<MultipartFile> images);

    void update(Long id, TourInfoRequest request, List<MultipartFile> images);

    void delete(Long id);

}
