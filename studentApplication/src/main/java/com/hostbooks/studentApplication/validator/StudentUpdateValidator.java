package com.hostbooks.studentApplication.validator;

import com.hostbooks.studentApplication.controller.StudentController;
import com.hostbooks.studentApplication.entities.Student;
import com.hostbooks.studentApplication.exception.StudentException;
import com.hostbooks.studentApplication.repository.StudentDao;
import com.hostbooks.studentApplication.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice(assignableTypes = StudentController.class)
@Qualifier("validator")
public class StudentUpdateValidator implements Validator {

    @Autowired
    private StudentService sService;


    @Override
    public boolean supports(Class<?> clazz) {

        return Student.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) throws StudentException {


//        ValidationUtils.rejectIfEmpty(errors, "gender","gender is required" );
//        ValidationUtils.rejectIfEmpty(errors,"email","email id is must required");

        Student student = (Student) target;

//        Integer studentCustom =   sService.findAvailableMobileNo(student.getCellPhone());
//      Student studentByMobileCustom=   sService.getStudentByMobileCustom(student.getCellPhone());
//
//        System.out.println("something1 : " + studentCustom);
//        if(studentCustom != 1 || studentCustom !=0 )
//        {
////            errors.rejectValue("name", null, "Enter the student name");
////            return;
////            System.out.println("something 2: " + number);
//            errors.rejectValue("cellPhone", "Student with same mobile no. is present update :");
//            return;
////            throw new StudentException("Student with same mobile no. is present : " + student.getCellPhone());
//
//        }
//        if (studentCustom!=0){
//            errors.rejectValue("cellPhone", "Student with same mobile no. is present :");
//            return;
//        }



    }
}
