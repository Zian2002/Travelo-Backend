package com.zian.travelo.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class StaffResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
}
