package com.hostbooks.studentApplication.validator;

import com.hostbooks.studentApplication.controller.StudentController;
import com.hostbooks.studentApplication.dto.StudentDto;
import com.hostbooks.studentApplication.entities.Course;
import com.hostbooks.studentApplication.entities.Student;
import com.hostbooks.studentApplication.exception.StudentException;
import com.hostbooks.studentApplication.repository.StudentDao;
import com.hostbooks.studentApplication.service.CourseService;
import com.hostbooks.studentApplication.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;

@ControllerAdvice(assignableTypes = StudentController.class)
@Qualifier("validator")
public class StudentValidator implements Validator {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Override
    public boolean supports(Class<?> clazz) {

        return StudentDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {


//        ValidationUtils.rejectIfEmpty(errors, "gender","gender is required" );
//        ValidationUtils.rejectIfEmpty(errors,"email","email id is must required");

        StudentDto student = (StudentDto) target;

        System.out.println("tum" + student);
        if(student.getName().length()<= 0 || student.getName()==null) {
            errors.rejectValue("name", "500", "Enter the student name");
        }


        List<Student> studentCustom =   studentService.getStudentByMobileCustom(student.getCellPhone());
//        Integer studentCustom =   studentService.getNumberStudentByMobileCustomService(student.getCellPhone());
        System.out.println("something1 : " + studentCustom);
        if(studentCustom !=null && studentCustom.size()!=0){
            errors.rejectValue("cellPhone", "500", "Student with same mobile no. is present : " + student.getCellPhone());

        }

//         Student studentCourse = courseService.getCourseByCourseId(student.getCourseId()).getStudent();
//        if(studentCourse !=null){
//            errors.rejectValue("courseId", "500", "student is already present in course : " + student.getCourseId());
//        }
    }
}
