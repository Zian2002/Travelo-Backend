package com.zian.travelo.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MethodNotAllowedException extends RuntimeException{
    private int errorCode = 405;
//    private String message =
    public MethodNotAllowedException(int errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public MethodNotAllowedException(String message){
        super(message);
    }

}
