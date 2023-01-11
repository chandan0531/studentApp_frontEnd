package com.example.studnetApp.service;

import com.example.studnetApp.entities.Student;
import com.example.studnetApp.exception.StudentException;
import com.example.studnetApp.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class StudentSeriveImpl implements StudentSerive {


    @Autowired
    private StudentDao stdDao;


    public String saveStudent(Student student) throws StudentException{

//      Optional<Student> opt =  stdDao.findById(student.getStdId());
//      if(opt.isPresent())
//      {
//          throw new StudentException("Student is already exist with " + student.getStdId());
//      }
//      else{
//          Student std = opt.get();
          stdDao.save(student);
          return "Added done";
//      }


    }

    public List<Student> getStudents() throws StudentException{


       List<Student> list = stdDao.findAll();

       if(list.size()> 0)
       {
           return list;
       }
       else
       {
           throw new StudentException("No student available");
       }
    }


    public Student getStudentById(Integer id) throws  StudentException{

        Optional<Student> option =  stdDao.findById(id);

        if(option.isPresent())
        {
            return option.get();
        }
        else
        {
            throw  new StudentException("Student not found with : " + id);
        }
    }


    public Student updateStudentById(Integer id, Student students) throws  StudentException{

        Optional<Student> opt =  stdDao.findById(id);
        if(opt.isPresent())
        {
            Student std = opt.get();

            std.setFirstName(students.getFirstName());
            std.setLastName(students.getLastName());
            std.setEmailId(students.getEmailId());

           return stdDao.save(std);
        }
        else {
            throw  new StudentException("Student not found with : " + id);
        }
    }

    public String deleteStudentById(Integer id) throws  StudentException{

        Optional<Student> opt =  stdDao.findById(id);
        if(opt.isPresent())
        {
            Student std = opt.get();

            stdDao.delete(std);
    return "deleted the student with id : " + id;

        }
        else {
            throw  new StudentException("Student not found with : " + id);
        }


    }

}
