package com.hostbooks.studentApplication.service;

import com.hostbooks.studentApplication.dto.StudentDto;
import com.hostbooks.studentApplication.entities.Student;
import com.hostbooks.studentApplication.exception.CourseException;
import com.hostbooks.studentApplication.exception.StudentException;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface StudentService {


    public Student saveStudents(StudentDto studentDto)throws StudentException;

    public List<Student> getAllStudent()throws StudentException;

    public Student updateStudentById(StudentDto std) throws StudentException;

    public String deleteStudentById(Integer Id) throws StudentException;

    public Student registerStudentInCourse(String cname, Student student)throws CourseException;


    public List<Student> findStudentsByName(String name)throws StudentException;

    public Student getStudentByStudentId(Integer studentId) throws  StudentException;


    public Integer findAvailableMobileNo( String mobile) throws StudentException;


    //criteria query
    public List<Student> getAllStudentCustom()throws StudentException;

    public List<Student> getStudentByMobileCustom(String mobile);

    public  List<Student> getAllStudentCustomDescS();

    public  Integer getNumberStudentByMobileCustomService(String mobile);

    public List<Student> getStudentByMobileCriteria(String mobile);

    //from DTO
    public  Student addStudentByDto(StudentDto studentDto);
}
