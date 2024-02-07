package com.zian.travelo.controller;

import com.zian.travelo.entity.Image;
import com.zian.travelo.model.response.SuccessResponse;
import com.zian.travelo.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
@Slf4j
public class ImageController {
    private final ImageService imageService;

    @Value("${pathImage}")
    private String pathImage;

    @PostMapping("/demo-upload")
    public ResponseEntity<SuccessResponse> demo(@RequestParam("data") String data,
                                                @RequestParam("images") List<MultipartFile> images){
        log.info(data);
        log.info(images.toString());
        images.forEach((image) -> {
            Image newImage = new Image();
            try {
                newImage.setImageUri(pathImage + imageService.saveImage("images/", image));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return ResponseEntity.ok(new SuccessResponse("upload successfully"));
    }


    @GetMapping("/{uriImage}")
    public ResponseEntity<byte[]> getImage(@PathVariable("uriImage") String uriImage) throws IOException {
        byte[] image = imageService.getImage("images/" + uriImage);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

}
