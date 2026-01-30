package com.sys_nova.pos_system.exciption;

// بنخليه يورث من RuntimeException عشان ميتعبناش في الـ Try/Catch في كل مكان
public class UserException extends Exception {

    public UserException(String message) {
        super(message);
    }
}