package com.zian.travelo.utils;

public class RegularExpression {

    public static boolean isValidMail(String email){
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(regex);
    }

    public static boolean isValidPhone(String phone){
        String regex = "^(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})$";
        return phone.matches(regex);
    }

    public static boolean isNotNull(String value){
        return value != null && !value.isEmpty();
    }



}
