package com.sys_nova.pos_system.payload.response;

import com.sys_nova.pos_system.payload.dto.UserDto;

import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;
    private String message;
    private UserDto user;
    private Boolean status;


    public String getJwt() { return jwt; }
    public void setJwt(String jwt) { this.jwt = jwt; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }


}
