package com.hostbooks.studentApplication.service;

import com.hostbooks.studentApplication.dto.CourseResponse;
import com.hostbooks.studentApplication.entities.Course;
import com.hostbooks.studentApplication.exception.CourseException;
import com.hostbooks.studentApplication.exception.StudentException;

import java.util.List;


public interface CourseService {

    public String saveCourseDetails(Course course) throws CourseException;

    public Course getCoursesByStudentId(Integer stdId) throws StudentException;

    public List<Course> getAllCourses() throws CourseException;

    public  Course getCourseByCourseId(Integer courseId) throws CourseException;

    public CourseResponse getAllCourseInPage(String tile, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

}
