package com.zian.travelo.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class LocationResponse {
    private Long id;
    private String name;
    private String province;
    private String description;
}
