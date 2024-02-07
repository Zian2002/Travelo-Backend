package com.zian.travelo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    String saveImage(String path, MultipartFile image) throws IOException;

    byte[] getImage(String filePath) throws IOException;

    void deleteImage(String filePath);
}
