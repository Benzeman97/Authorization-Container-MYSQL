package com.benz.identity.server.db;

public enum EPermission {

    CAN_CREATE(10,"CREATE"),
    CAN_READ(20,"READ"),
    CAN_UPDATE(30,"UPDATE"),
    CAN_DELETE(40,"DELETE");

    private final int id;
    private final String value;

     EPermission(int id,String value){
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
