package com.benz.identity.server.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String msg){
        super(msg);
    }
}
