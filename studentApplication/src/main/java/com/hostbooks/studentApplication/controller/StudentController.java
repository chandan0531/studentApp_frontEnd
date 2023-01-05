package com.hostbooks.studentApplication.controller;


import com.hostbooks.studentApplication.dto.CriteriaResponse;
import com.hostbooks.studentApplication.dto.PageConstants;
import com.hostbooks.studentApplication.dto.StudentDto;
import com.hostbooks.studentApplication.dto.StudentResponse;
import com.hostbooks.studentApplication.entities.Course;
import com.hostbooks.studentApplication.entities.Student;
import com.hostbooks.studentApplication.exception.MyError;
import com.hostbooks.studentApplication.repository.CustomDaoInterface;
import com.hostbooks.studentApplication.service.AddressService;
import com.hostbooks.studentApplication.service.StudentService;
import com.hostbooks.studentApplication.validator.StudentUpdateValidator;
import com.hostbooks.studentApplication.validator.StudentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
//@RequestMapping("/api/students")
@CrossOrigin("http://localhost:4200/")
public class StudentController {

    @Autowired
    private StudentService sService;

    @Autowired
    private AddressService aService;

    @Autowired
    private CustomDaoInterface customDaoInterface;

    @Qualifier("validator")
    @Autowired
    private StudentValidator studentValidator;
    @InitBinder
    //("Student")
    public void initBinder(WebDataBinder binder){
        binder.setValidator(studentValidator);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("api/students")
    ResponseEntity<?> createStudentController(@Valid @RequestBody StudentDto studentDto, Errors errors, WebRequest webRequest){

        if(errors.hasErrors()){

            MyError myError = new MyError(LocalDate.now(), errors.getFieldError().getDefaultMessage(), webRequest.getDescription(false));
            return  new ResponseEntity<>(myError, HttpStatus.OK);
        }
        Student  stds =  sService.saveStudents(studentDto);
        return new ResponseEntity<>(stds,HttpStatus.CREATED);
    }



    @GetMapping("api/students/{Id}")
    ResponseEntity<Student> getStudentByStudentIdController(@PathVariable Integer Id){

        Student stds =  sService.getStudentByStudentId(Id);

        return new ResponseEntity<Student>(stds,HttpStatus.ACCEPTED);
    }

    @GetMapping("api/students")
    ResponseEntity<List<Student>> getAllStudentController(){
        List<Student> stds = sService.getAllStudent();

        return new ResponseEntity<List<Student>>(stds,HttpStatus.ACCEPTED);
    }




    @PutMapping("api/students")
    ResponseEntity<?> updateStudentByIdController( @RequestBody StudentDto std ){

        Student stds =  sService.updateStudentById(std);

        return new ResponseEntity<Student>(stds,HttpStatus.ACCEPTED);
    }



    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("api/students/{Id}")
    ResponseEntity<String> deleteStudentByIdController(@PathVariable Integer Id){


        String message =  sService.deleteStudentById(Id);

        return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
    }


//    @GetMapping("api/students/{name}")
//    ResponseEntity<List<Student>> getStudentsByNameController(@PathVariable String name){
//        List<Student> stds = sService.findStudentsByName(name);
//
//        return new ResponseEntity<List<Student>>(stds,HttpStatus.ACCEPTED);
//    }



    //custom criteria query
    @GetMapping("api/studentCustom")
    ResponseEntity<List<Student>> getAllStudentCustom(){
        List<Student> stds = sService.getAllStudentCustom();

        return new ResponseEntity<List<Student>>(stds,HttpStatus.ACCEPTED);
    }

    @GetMapping("api/studentCustom/{mobile}")
    ResponseEntity<List<Student>> getAllStudentByMobileCustom( @PathVariable String mobile){
        List<Student> stds = sService.getStudentByMobileCustom(mobile);

        return new ResponseEntity<List<Student>>(stds,HttpStatus.ACCEPTED);
    }

    @GetMapping("api/studentCustomDes")
    ResponseEntity<List<Student>> getAllStudentDescIdCustom(){
        List<Student> stds = sService.getAllStudentCustomDescS();

        return new ResponseEntity<List<Student>>(stds,HttpStatus.ACCEPTED);
    }


    @GetMapping("api/studentCustomNumber/{mobile}")
    ResponseEntity<Integer> getNumberOfStudentByMobileCustom( @PathVariable String mobile){
        Integer stds = sService.getNumberStudentByMobileCustomService(mobile);

        return new ResponseEntity<Integer>(stds,HttpStatus.ACCEPTED);
    }



    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("api/studentsCustom")
    ResponseEntity<?> addStudentByDTOController(@RequestBody StudentDto studentDto, Errors error){
//        System.out.println("kuch v karo");
//        if(error.hasErrors())
//        {
////            MyError myError = new MyError(LocalDate.now(), error.getFieldError().getDefaultMessage(), wr.getDescription(false));
//            return  new ResponseEntity<>("hi bro",HttpStatus.OK);
//        }

        Student  stds =  sService.addStudentByDto(studentDto);
        return new ResponseEntity<>(stds,HttpStatus.CREATED);
    }


    @GetMapping("api/studentsCriteria/{mobile}")
    ResponseEntity<List<Student>> getAllStudentByMobileCriteria( @PathVariable String mobile){
        List<Student> stds = sService.getStudentByMobileCriteria(mobile);

        return new ResponseEntity<List<Student>>(stds,HttpStatus.ACCEPTED);
    }



    @GetMapping("api/studentPageCriteria")
    ResponseEntity<StudentResponse> getAllStudentsByPageControllerCriteria(
            @RequestParam(value = "title", defaultValue = "", required = false) String title,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "studentId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){

        StudentResponse studentResponse = sService.getAllStudentByPaginationCriteria(title, pageNo,pageSize, sortBy, sortDir);

        return new  ResponseEntity<>(studentResponse, HttpStatus.ACCEPTED);
    }


    @GetMapping("api/studentHQL")
    ResponseEntity<List<Student>> getAllStudentHQL(){
        List<Student> stds = sService.getAllStudentHQL();

        return new ResponseEntity<List<Student>>(stds,HttpStatus.ACCEPTED);
    }


    @GetMapping("api/studentPageHQL")
    ResponseEntity<StudentResponse> getAllStudentsByPageControllerHQL(
            @RequestParam(value = "title", defaultValue = "", required = false) String title,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
            @RequestParam(value = "pageSize", defaultValue = "2", required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = "studentId", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){

        StudentResponse studentResponse = customDaoInterface.getStudentPaginationHQL(title, pageNo,pageSize, sortBy, sortDir);

        return new  ResponseEntity<>(studentResponse, HttpStatus.ACCEPTED);
    }



    @GetMapping("api/courseNamedQuery/{name}")
    ResponseEntity<List<Course>> getAllCourseByName(@PathVariable String name){

       List<Course> courses =  customDaoInterface.getCourseByNameQuery(name);

        return new ResponseEntity<>(courses,HttpStatus.ACCEPTED);
    }


    @GetMapping("joinHql")
    ResponseEntity<List<Object[]>> getStudentJoinCourse(){

        List<Object[]> join =  customDaoInterface.getDetailsJoin();

        return new ResponseEntity<>(join,HttpStatus.ACCEPTED);
    }

//    @PutMapping("updateJoinHql")
//    ResponseEntity<String> updateJoinCourse(){
//
//       String join =  customDaoInterface.updateCourseHql();
//
//        return new ResponseEntity<>(join,HttpStatus.ACCEPTED);
//    }
}
