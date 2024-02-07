package com.zian.travelo.service.impl;

import com.zian.travelo.service.ImageService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public String saveImage(String path, MultipartFile image) throws IOException {
        String originalFileName = image.getOriginalFilename();
        String extension = StringUtils.getFilenameExtension(originalFileName);
        if (extension == null)
            return null;
        String imageName = UUID.randomUUID().toString()+"."+extension;

        File file = new File(path + imageName);
        FileUtils.writeByteArrayToFile(file, image.getBytes());
        System.out.println(imageName);
        return imageName;
    }

    @Override
    public byte[] getImage(String filePath) throws IOException {
        return FileUtils.readFileToByteArray(new File(filePath));
    }

    @Override
    public void deleteImage(String filePath) {
        File file = new File(filePath);
        file.deleteOnExit();
    }
}
