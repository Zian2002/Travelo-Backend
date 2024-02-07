package com.zian.travelo.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterRequest {
    private String email;
    private String password;
    private String name;
    private String phone;
    private String address;
}
