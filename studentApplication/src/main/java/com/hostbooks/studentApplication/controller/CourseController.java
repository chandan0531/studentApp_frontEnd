package com.hostbooks.studentApplication.controller;

import com.hostbooks.studentApplication.dto.CriteriaResponse;
import com.hostbooks.studentApplication.dto.PageConstants;
import com.hostbooks.studentApplication.dto.CourseResponse;
import com.hostbooks.studentApplication.entities.Course;
import com.hostbooks.studentApplication.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("api/course")
    ResponseEntity<String> saveCourseController(@RequestBody Course course){

        String message = courseService.saveCourseDetails(course);

        return  new ResponseEntity<String>(message, HttpStatus.ACCEPTED);

    }

    @GetMapping("api/course")
    ResponseEntity<List<Course>> getAllCoursesController(){

        List<Course> courses = courseService.getAllCourses();

        return  new ResponseEntity<List<Course>>(courses, HttpStatus.ACCEPTED);

    }

    @GetMapping("api/course/{courseId}")
    ResponseEntity<Course> getCourseByStudentIdController(@Valid @PathVariable Integer courseId){

       Course courses = courseService.getCourseByCourseId(courseId);

       return new  ResponseEntity<Course>(courses, HttpStatus.ACCEPTED);
    }

    @GetMapping("api/coursePage")
    ResponseEntity<CourseResponse> getAllCourseByPageController(
            @RequestParam(value = "title", defaultValue = "", required = false) String title,
            @RequestParam(value = "pageNo", defaultValue = PageConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = PageConstants.DEFAULT_SORT_BY, required = false) String sortBy,
             @RequestParam(value = "sortDir", defaultValue = PageConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){

            CourseResponse courses = courseService.getAllCourseInPage(title,pageNo,pageSize,sortBy,sortDir);

        return new  ResponseEntity<>(courses, HttpStatus.ACCEPTED);
    }



    @GetMapping("api/coursePageCriteria")
    ResponseEntity<CriteriaResponse> getAllCourseByPageControllerCriteria(
            @RequestParam(value = "title", defaultValue = "", required = false) String title,
            @RequestParam(value = "pageNo", defaultValue = PageConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = PageConstants.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = PageConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PageConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){

        CriteriaResponse courses = courseService.getAllCourseByPaginationCriteria(title, pageNo,pageSize, sortBy, sortDir);

        return new  ResponseEntity<>(courses, HttpStatus.ACCEPTED);
    }



}
