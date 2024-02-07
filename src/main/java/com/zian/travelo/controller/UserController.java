package com.zian.travelo.controller;

import com.zian.travelo.model.request.LoginRequest;
import com.zian.travelo.model.request.RegisterRequest;
import com.zian.travelo.model.response.AuthenticationResponse;
import com.zian.travelo.model.response.MessageResponse;
import com.zian.travelo.model.response.SuccessResponse;
import com.zian.travelo.service.UserService;
import com.zian.travelo.utils.Authen;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<SuccessResponse> register(@RequestBody RegisterRequest request){
        userService.createNewUser(request);
        return ResponseEntity.ok(new SuccessResponse("create new user successfully"));
    }

    @GetMapping("/detail")
    public ResponseEntity<MessageResponse> getDetail(){
        String email = Authen.getEmail();
        return ResponseEntity.ok(new MessageResponse(email));
    }

}
