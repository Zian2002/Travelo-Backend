package com.zian.travelo.service.impl;

import com.zian.travelo.entity.Image;
import com.zian.travelo.entity.Location;
import com.zian.travelo.exception.NotFoundException;
import com.zian.travelo.mapper.LocationMapper;
import com.zian.travelo.model.dto.LocationDTO;
import com.zian.travelo.model.request.LocationRequest;
import com.zian.travelo.repository.ImageRepository;
import com.zian.travelo.repository.LocationRepository;
import com.zian.travelo.service.ImageService;
import com.zian.travelo.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository repository;
    private final ImageService imageService;
    private final ImageRepository imageRepository;

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
    public void add(LocationRequest request, List<MultipartFile> images) throws IOException {
        Location location = Location.builder()
                .name(request.getName())
                .description(request.getDescription())
                .province(request.getProvince())
                .status(true)
                .build();

        List<Image> list = new ArrayList<Image>();
        if (images != null){
            images.forEach((image) -> {
                Image newImage = new Image();
                try {
                    newImage.setImageUri(imageService.saveImage("images/", image));
                    list.add(newImage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        location.setImages(list);
        repository.save(location);
    }

    @Override
    public void update(Long id, LocationRequest request, List<MultipartFile> images) {
        Location location = getLocationById(id);
        List<Image> oldImages = location.getImages();
        List<String> requestImage = request.getImages();
        List<Image> updateImages = new ArrayList<>(requestImage.stream().map(imageRepository::findByImageUri).toList());
        oldImages.forEach(old -> {
            if (!updateImages.contains(old)){
                imageService.deleteImage("images/" + old.getImageUri());
                imageRepository.delete(old);

                System.out.println(old.getImageUri() + "has deleted");
            }else {
                System.out.println("normal");
            }
        });

        Location newLocation = Location.builder()
                .id(location.getId())
                .name(request.getName() != null ? request.getName() : location.getName())
                .description(request.getDescription() != null ? request.getDescription() : location.getDescription())
                .province(request.getProvince() != null ? request.getProvince(): location.getProvince())
                .status(location.getStatus())
                .images(location.getImages())
                .build();
        images.forEach((image) -> {
            Image newImage = new Image();
            try {
                String uri = imageService.saveImage("images/", image);
                if (uri != null) {
                    newImage.setImageUri(uri);
                    updateImages.add(newImage);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        newLocation.setImages(updateImages);
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
