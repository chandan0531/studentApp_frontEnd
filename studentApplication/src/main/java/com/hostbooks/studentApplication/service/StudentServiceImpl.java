package com.hostbooks.studentApplication.service;

import java.util.List;
import java.util.Optional;

import com.hostbooks.studentApplication.dto.StudentDto;
import com.hostbooks.studentApplication.entities.Address;
import com.hostbooks.studentApplication.entities.Course;
import com.hostbooks.studentApplication.entities.Student;
import com.hostbooks.studentApplication.exception.CourseException;
import com.hostbooks.studentApplication.exception.StudentException;
import com.hostbooks.studentApplication.repository.AddressDao;
import com.hostbooks.studentApplication.repository.CourseDao;
import com.hostbooks.studentApplication.repository.CustomDaoInterface;
import com.hostbooks.studentApplication.repository.StudentDao;
import com.hostbooks.studentApplication.validator.StudentValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class StudentServiceImpl implements StudentService{


    @Autowired
    private StudentDao sDao;

    @Autowired
    private AddressDao aDao;

    @Autowired
    private CourseDao cDao;


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomDaoInterface customDaoInterface;

    @Override
    public Student saveStudents(StudentDto studentDto) throws StudentException {

        Student student = modelMapper.map(studentDto, Student.class);

        Optional<Course> opt = cDao.findById(studentDto.getCourseId());
        if (!opt.isPresent()) {
            throw new CourseException("Course is not available");
        } else {

            Course course = opt.get();
            student.setCourse(course);

            if (course.getStudent() == null) {
                Integer listStudent = sDao.findAvailableMobileNo(student.getCellPhone());
//               Student studentCustom =  customDaoInterface.getStudentByMobileCustom(studentDto.getCellPhone());

//               if (listStudent == 0) {
                    course.setStudent(student);
                    return sDao.save(student);
//                } else {
//                    throw new StudentException("Student with same mobile no. is present : " + student.getCellPhone());
//                }

            } else {
                throw new CourseException("student is already present in course : " + studentDto.getCourseId());
            }


        }
    }

    @Override
    public List<Student> getAllStudent() throws StudentException {

        List<Student> students = sDao.findAll();

        if (students.size() > 0) {
            return students;
        } else {
            throw new StudentException("Employee Not present");
        }

    }


    @Override
    public Student updateStudentById(StudentDto std) throws StudentException {

        Optional<Student> opt = sDao.findById(std.getStudentId());

        if (opt.isPresent()) {

            Student student = opt.get();
//            student.setName(std.getName());
//            student.setGender(std.getGender());
//            student.setEmail(std.getEmail());
//            student.setCellPhone(std.getCellPhone());

            Integer count = sDao.findAvailableMobileNo(std.getCellPhone());

            if (count == 1 && opt.get().getCellPhone().equals(std.getCellPhone())) {
                student.setName(std.getName());
                student.setGender(std.getGender());
                student.setEmail(std.getEmail());
                student.setCellPhone(std.getCellPhone());

                return sDao.save(student);
            }
            if (count == 0) {
                student.setName(std.getName());
                student.setGender(std.getGender());
                student.setEmail(std.getEmail());
                student.setCellPhone(std.getCellPhone());

                return sDao.save(student);
            }
            else {
                throw new StudentException("Student with same mobile no. is present : " + std.getCellPhone());
            }

        } else {
            throw new StudentException("Student Not present with: " + std.getStudentId());
        }
    }

    @Override
    public String deleteStudentById(Integer Id) throws StudentException {


        Optional<Student> opt = sDao.findById(Id);

        if (opt.isPresent()) {
            Student std = opt.get();
//            Course course = std.getCourse(); //new line added

            sDao.delete(std);

//            cDao.save(course); //new line added
//            System.out.println(course + "chandan");
            return "Student details have been deleted with :  + Id";
        } else {
            throw new StudentException("Student Not present with: " + Id);
        }
    }

    @Override
    public Student registerStudentInCourse(String cname, Student student) throws CourseException {

        Course course = cDao.findByCourseName(cname);

        if (course != null) {

//            course.getStudents().add(student);
//            student.getCourses().add(course);
            course.setStudent(student);
            student.setCourse(course);

            return sDao.save(student);
        } else {
            throw new CourseException("Course Does not exist with Cname " + cname);
        }
    }

    @Override
    public List<Student> findStudentsByName(String name) throws StudentException {

        List<Student> students = sDao.findByName(name);

        if (students.size() > 0) {
            return students;
        } else {
            throw new StudentException("Student not exist with the name:  " + name);
        }


    }

    @Override
    public Student getStudentByStudentId(Integer studentId) throws StudentException {

        Optional<Student> optional = sDao.findById(studentId);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new StudentException("Student not found with : " + studentId);
        }
    }

    @Override
    public Integer findAvailableMobileNo(String mobile) throws StudentException {

        Integer number = sDao.findAvailableMobileNo(mobile);

        return number;
    }

    @Override
    public List<Student> getAllStudentCustom() throws StudentException {
       List<Student> students =  customDaoInterface.getAllStudentCustom();
       return students;
    }

    @Override
    public  List<Student>  getStudentByMobileCustom(String mobile) {

       List<Student> student =  customDaoInterface.getStudentByMobileCustom(mobile);


        return student;
    }

    @Override
    public List<Student> getAllStudentCustomDescS() {

        List<Student> students = customDaoInterface.getAllStudentCustomDesc();
        return students;
    }

    @Override
    public Integer getNumberStudentByMobileCustomService(String mobile) {

        Integer number = customDaoInterface.getNumberStudentByMobileCustom(mobile);
        return number;
    }

    @Override
    public List<Student> getStudentByMobileCriteria(String mobile) {
       List<Student> list =  customDaoInterface.getStudentByMobileCriteria(mobile);
       return list;
    }

    @Override
    public Student addStudentByDto(StudentDto studentDto) {


        Student student = modelMapper.map(studentDto, Student.class);

        Optional<Course> opt = cDao.findById(studentDto.getCourseId());
        if (!opt.isPresent()) {
            throw new CourseException("Course is not available");
        } else {

            Course course = opt.get();
            student.setCourse(course);

            if (course.getStudent() == null) {
                Integer listStudent = sDao.findAvailableMobileNo(student.getCellPhone());
                if (listStudent == 0) {
                    course.setStudent(student);
                    return sDao.save(student);
                } else {
                    throw new StudentException("Student with same mobile no. is present : " + student.getCellPhone());
                }

            } else {
                throw new CourseException("student is already present in course : " + studentDto.getCourseId());
            }


        }
    }


}
