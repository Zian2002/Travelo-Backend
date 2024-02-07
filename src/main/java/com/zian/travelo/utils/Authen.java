package com.zian.travelo.utils;

import com.zian.travelo.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class Authen {
    public static String getEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        log.info("current user: " + username);
        if (username == null || username.equals("anonymousUser"))
            throw new BadRequestException("User is not login");
        return username;
    }
}
