package com.zian.travelo.service.impl;

import com.zian.travelo.entity.Customer;
import com.zian.travelo.entity.ERole;
import com.zian.travelo.entity.Role;
import com.zian.travelo.entity.User;
import com.zian.travelo.exception.BadRequestException;
import com.zian.travelo.exception.NotFoundException;
import com.zian.travelo.model.request.LoginRequest;
import com.zian.travelo.model.request.RegisterRequest;
import com.zian.travelo.model.response.AuthenticationResponse;
import com.zian.travelo.repository.CustomerRepository;
import com.zian.travelo.repository.RoleRepository;
import com.zian.travelo.repository.UserRepository;
import com.zian.travelo.security.service.JwtService;
import com.zian.travelo.service.UserService;
import com.zian.travelo.utils.BcryptUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final JwtService<String> jwtService;

    @Override
    public AuthenticationResponse login(LoginRequest request) {
        if (request.getEmail() == null || request.getPassword() == null)
            throw new BadRequestException("Please fill full info");

        if (!userRepository.existsByEmail(request.getEmail()))
            throw new NotFoundException("Email is not found or not register");

        User user = userRepository.findByEmail(request.getEmail()).get();
        if (!BcryptUtils.verifyPassword(request.getPassword(), user.getPassword())){
            throw new BadRequestException("Password not correct");
        }

        String token = jwtService.generateToken(user.getEmail());
        return AuthenticationResponse.builder()
                .email(user.getEmail())
                .role(user.getRole().getName().name())
                .token(token)
                .build();
    }

    @Override
    public void createNewUser(RegisterRequest request) {
        Map<String, String> infoMessage = new HashMap<String, String>();
        if (request.getEmail() == null){
            infoMessage.put("email","Email must be required.");
        }
        if (request.getName() == null){
            infoMessage.put("name","Name must be required.");
        }
        if (request.getPhone() == null){
            infoMessage.put("phone","Phone must be required.");
        }
        if (request.getPassword() == null){
            infoMessage.put("password","Password must be required.");
        }
        if (request.getAddress() == null){
            infoMessage.put("address","address must be required.");
        }

        if (!infoMessage.isEmpty()){
            throw new BadRequestException("Bad request", infoMessage);
        }

        if (userRepository.existsByEmail(request.getEmail())){
            throw new BadRequestException("Email is exists");
        }

        Role role = roleRepository.findByName(ERole.ROLE_USER);
        if (role == null){
            role = Role.builder()
                    .name(ERole.ROLE_USER)
                    .build();
//            role = roleRepository.save(role);
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(BcryptUtils.hashPassword(request.getPassword()))
                .role(role)
                .active(true)
                .build();

        Customer customer = customerRepository.findByEmail(request.getEmail()).orElse(null);
        if (customer == null){
            customer = Customer.builder()
                    .email(request.getEmail())
                    .name(request.getName())
                    .phone(request.getPhone())
                    .address(request.getAddress())
                    .status(true)
                    .user(user)
                    .build();
        }

        System.out.println("pretty end");
        customerRepository.save(customer);
        System.out.println("end");


    }
}
