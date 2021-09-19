package com.benz.identity.server.db;

public enum ERole {

    ROLE_USER(101,"USER"),
    ROLE_MODERATOR(102,"MODERATOR"),
    ROLE_ADMIN(103,"ADMIN");

    private final int id;
    private final String value;

    ERole(int id,String value){
        this.id=id;
        this.value=value;
    }

    public int getId(){
        return id;
    }

    public String getValue(){
        return value;
    }
}
