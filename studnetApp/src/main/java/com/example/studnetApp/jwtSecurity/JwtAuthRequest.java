package com.example.studnetApp.jwtSecurity;


import lombok.Data;

@Data
public class JwtAuthRequest {
    private  String username;
    private  String password;
}
