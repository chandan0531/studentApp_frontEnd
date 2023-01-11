package com.example.studnetApp.controller;

import com.example.studnetApp.userautho.Role;
import com.example.studnetApp.userautho.User;
import com.example.studnetApp.userrepository.RoleServiceImpl;
import com.example.studnetApp.userrepository.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleServiceImpl roleService;
    @PostMapping("api/users")
    ResponseEntity<User> saveUserController(@RequestBody User user){
        User ur = userService.save(user);
        return new ResponseEntity<>(ur, HttpStatus.ACCEPTED);
    }

//    @GetMapping("api/users")
//    ResponseEntity<List<User>> getAllUserController(){
//        List<User> ur = userService.get
//        return new ResponseEntity<>(ur, HttpStatus.ACCEPTED);
//    }

    @PostMapping("api/role/{id}")
    ResponseEntity<Role> saveUserController(@PathVariable long id, @RequestBody Role role){
        Role ur = roleService.saveRole(id,role);
        return new ResponseEntity<>(ur, HttpStatus.ACCEPTED);
    }

}
