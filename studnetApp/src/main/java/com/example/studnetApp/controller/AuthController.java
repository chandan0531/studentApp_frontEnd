package com.example.studnetApp.controller;

import com.example.studnetApp.jwtSecurity.JwtAuthRequest;
import com.example.studnetApp.jwtSecurity.JwtAuthResponse;
import com.example.studnetApp.jwtSecurity.JwtTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/jwt/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest){

        this.authenticate(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword());
        UserDetails userDetails =  this.userDetailsService.loadUserByUsername(jwtAuthRequest.getUsername());

       String token =  this.jwtTokenHelper.generateToken(userDetails);

       JwtAuthResponse jwtAuthResponse  =new JwtAuthResponse();
       jwtAuthResponse.setToken(token);
       return  new ResponseEntity<>(jwtAuthResponse, HttpStatus.ACCEPTED);
    }

    public void authenticate(String username, String password){

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

}
