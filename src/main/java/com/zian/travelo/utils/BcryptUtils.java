package com.zian.travelo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptUtils {
    private static final BCryptPasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    public static String hashPassword(String plainTextPassword){
        return PASSWORD_ENCODER.encode(plainTextPassword);
    }

    public static boolean verifyPassword(String plainTextPassword, String hashedPassword){
        return PASSWORD_ENCODER.matches(plainTextPassword, hashedPassword);
    }
}
