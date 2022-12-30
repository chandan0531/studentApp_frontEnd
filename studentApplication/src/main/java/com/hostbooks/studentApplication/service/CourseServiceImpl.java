package com.hostbooks.studentApplication.service;

import com.hostbooks.studentApplication.dto.CourseResponse;
import com.hostbooks.studentApplication.entities.Course;
import com.hostbooks.studentApplication.entities.Student;
import com.hostbooks.studentApplication.exception.CourseException;
import com.hostbooks.studentApplication.exception.StudentException;
import com.hostbooks.studentApplication.repository.CourseDao;
import com.hostbooks.studentApplication.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseDao cDao;

    @Autowired
    private StudentDao sDao;

    @Override
    public String saveCourseDetails(Course course) throws CourseException {

            cDao.save(course);
            return "Course added";


    }

    @Override
    public Course getCoursesByStudentId(Integer stdId) throws StudentException {

        Optional<Student> optional =  sDao.findById(stdId);
        if(!optional.isPresent())
        {
          throw  new StudentException("Student not found with : " + stdId);
        }
        else
        {
            Student student = optional.get();
            Course courses = student.getCourse();

                return courses;

        }

    }

    @Override
    public List<Course> getAllCourses() throws CourseException {

        List<Course> courses = cDao.findAll();
        if(courses.size()>0)
        {
            return courses;
        }
        else
        {
            throw new CourseException("No courses available");
        }
    }

    @Override
    public Course getCourseByCourseId(Integer courseId) throws CourseException {

       Optional<Course> optional =  cDao.findById(courseId);
        if(optional.isPresent())
        {
           System.out.println(optional.get().getStudent().getStudentId());
            return optional.get();
        }
        else
        {
            throw  new CourseException("No course is not with : " + courseId);
        }
    }

    @Override
    public CourseResponse getAllCourseInPage(String tile,Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc")){
            sort = Sort.by(sortBy).ascending();
        }
        else {
            sort = Sort.by(sortBy).descending();
        }

//        Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable p =  PageRequest.of(pageNumber,pageSize, sort);


        if(tile.equalsIgnoreCase("")){
            Page<Course>  pageCourse =  cDao.findAll(p);

            List<Course>  allCourse =  pageCourse.getContent();

            CourseResponse courseResponse = new CourseResponse();

            courseResponse.setContent(allCourse);
            courseResponse.setPageNo(pageCourse.getNumber());
            courseResponse.setPageSize(pageCourse.getSize());
            courseResponse.setTotalElements(pageCourse.getTotalElements());
            courseResponse.setTotalPages(pageCourse.getTotalPages());
            courseResponse.setLast(pageCourse.isLast());

            return courseResponse;
        }

        else{
//            Page<Course>  pageCourse  = cDao.findByCourseName(tile, p);

            Page<Course>  pageCourse  = cDao.search(tile, p);

            List<Course>  allCourse =  pageCourse.getContent();

            CourseResponse courseResponse = new CourseResponse();

            courseResponse.setContent(allCourse);
            courseResponse.setPageNo(pageCourse.getNumber());
            courseResponse.setPageSize(pageCourse.getSize());
            courseResponse.setTotalElements(pageCourse.getTotalElements());
            courseResponse.setTotalPages(pageCourse.getTotalPages());
            courseResponse.setLast(pageCourse.isLast());

            return courseResponse;
        }



    }


}
