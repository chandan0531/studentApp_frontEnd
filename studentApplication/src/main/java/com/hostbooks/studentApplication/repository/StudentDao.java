package com.hostbooks.studentApplication.repository;

import com.hostbooks.studentApplication.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao extends JpaRepository<Student,Integer> {

    public List<Student> findByName(String name);

//    @Query("from Student where cell_phone=:c")
//    public List<Student>  findAvailableMobileNo( @Param("c") String mobile);


    @Query("Select count(*) from Student where cell_phone=:c")
    public Integer findAvailableMobileNo( @Param("c") String mobile);


//    @Query("Select * from Student where cell_phone=:c")
//    public Student findAvailableStudentByMobileNo( @Param("c") String mobile);

}
