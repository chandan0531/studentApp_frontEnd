package com.hostbooks.studentApplication.userrepo;

import com.hostbooks.studentApplication.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    public User findByUserName(String userName);
}
