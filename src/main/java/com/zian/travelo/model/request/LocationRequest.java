package com.zian.travelo.model.request;

import com.zian.travelo.entity.Image;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class LocationRequest {
    private String name;
    private String province;
    private String description;
    private List<String> images;
}
