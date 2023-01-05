package com.hostbooks.studentApplication.repository;

import com.hostbooks.studentApplication.dto.CriteriaResponse;
import com.hostbooks.studentApplication.dto.StudentResponse;
import com.hostbooks.studentApplication.entities.Course;
import com.hostbooks.studentApplication.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomDaoInterface {

    List<Student> getAllStudentCustom();

    List<Student>  getStudentByMobileCustom(String mobile);

    List<Student> getAllStudentCustomDesc();

    Integer getNumberStudentByMobileCustom(String mobile);


    List<Student> getStudentByMobileCriteria(String mobile);


    CriteriaResponse getAllCourseByPagination(String name, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    public CriteriaResponse getFilterCourses(String name, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    public StudentResponse getFilterStudent(String name, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //HQL
    public List<Student> getAllStudentHQL();

    public StudentResponse getStudentPaginationHQL(String name, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //namedQuery
    public List<Course> getCourseByNameQuery(String name);

    // join HQL
    public List<Object[]> getDetailsJoin();

    public String updateCourseHql();

}
