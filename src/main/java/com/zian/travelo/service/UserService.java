package com.zian.travelo.service;

import com.zian.travelo.model.request.LoginRequest;
import com.zian.travelo.model.request.RegisterRequest;
import com.zian.travelo.model.response.AuthenticationResponse;

public interface UserService {
    AuthenticationResponse login(LoginRequest request);

    void createNewUser(RegisterRequest request);

}
