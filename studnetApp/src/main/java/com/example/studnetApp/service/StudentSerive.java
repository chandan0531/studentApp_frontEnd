package com.example.studnetApp.service;

import com.example.studnetApp.entities.Student;
import com.example.studnetApp.exception.StudentException;

import java.util.List;

public interface StudentSerive {

    public String saveStudent(Student student) throws StudentException;

    public List<Student> getStudents() throws StudentException;

    public Student getStudentById(Integer id) throws  StudentException;


    public Student updateStudentById(Integer id, Student students) throws  StudentException;

    public String deleteStudentById(Integer id) throws  StudentException;
}
