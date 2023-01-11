package com.example.studnetApp.controller;

import com.example.studnetApp.entities.Student;
import com.example.studnetApp.service.StudentSerive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")

public class StudentController {

    @Autowired
    private StudentSerive sService;
    @GetMapping("/hi")
    public String getName(){

        return "chandan kumar sharma";
    }


    @PostMapping("api/student")
    ResponseEntity<String> saveStudent( @RequestBody Student student){
        String std = sService.saveStudent(student);
        return new ResponseEntity<String>(std, HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/students")
    ResponseEntity<List<Student>> getStudents(){
        List<Student> list = sService.getStudents();
        return new ResponseEntity<List<Student>>(list, HttpStatus.ACCEPTED);
    }

    @GetMapping("api/student/{id}")
    ResponseEntity<Student> getStudentById(@PathVariable Integer id){

        Student student = sService.getStudentById(id);
        return new ResponseEntity<Student>(student, HttpStatus.ACCEPTED);

    }
    @PutMapping("api/student/{id}")
    ResponseEntity<Student> updateStudentById( @PathVariable Integer id, @RequestBody Student students){

        Student student = sService.updateStudentById(id, students);
        return new ResponseEntity<Student>(student, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("api/student/{id}")
    ResponseEntity<String> deleteStudentById(@PathVariable Integer id){

        String message = sService.deleteStudentById(id);
        return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);

    }


}
