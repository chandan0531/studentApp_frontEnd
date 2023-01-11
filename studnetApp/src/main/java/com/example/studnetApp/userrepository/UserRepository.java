package com.example.studnetApp.userrepository;

import com.example.studnetApp.userautho.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    public  User findByUserName(String userName);
//
//    @Query("SELECT u FROM User u Where u.userName = :username")
//    public  User getUserByUserName(@Param("username") String userName);

    User findByEmail(String email);
}
