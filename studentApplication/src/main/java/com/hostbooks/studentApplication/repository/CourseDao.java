package com.hostbooks.studentApplication.repository;

import com.hostbooks.studentApplication.dto.CourseResponse;
import com.hostbooks.studentApplication.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDao extends JpaRepository<Course, Integer> {
    public Course findByCourseName(String cname);

    public Page<Course> findByCourseName(String name, Pageable pageable);

//    @Query("SELECT c FROM COURSE c WHERE c.courseName LIKE:name" + "OR c.courseType LIKE:name")
//    public Page<Course> findByAnyTitle(@Param("name") String name, Pageable pageable);


//    OR (:name is null or c.student.name = :name)
    @Query("SELECT c FROM Course c WHERE (:name is null or c.courseName = :name) OR (:name is null or c.courseType = :name) OR (:name is null or c.topics = :name) OR (:name is null or c.courseId = :name)")
    Page<Course> search(@Param("name") String name,  Pageable pageable);

}
