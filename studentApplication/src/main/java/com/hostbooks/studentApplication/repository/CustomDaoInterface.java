package com.hostbooks.studentApplication.repository;

import com.hostbooks.studentApplication.entities.Course;
import com.hostbooks.studentApplication.entities.Student;

import java.util.List;

public interface CustomDaoInterface {

    List<Student> getAllStudentCustom();

    List<Student>  getStudentByMobileCustom(String mobile);

    List<Student> getAllStudentCustomDesc();

    Integer getNumberStudentByMobileCustom(String mobile);


    List<Student> getStudentByMobileCriteria(String mobile);


    List<Course> getAllCourseByPagination(Integer pageNumber, Integer pageSize);
}
