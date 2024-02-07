package com.zian.travelo.model.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LocationRequest {
    private String name;
    private String province;
    private String description;
    private List<String> images;
}
