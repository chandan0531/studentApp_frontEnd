package com.hostbooks.studentApplication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200/")
public class BasicAuth {



    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("api/login")
    public String getAuthorized(){

        return "Authorized";
    }
}
