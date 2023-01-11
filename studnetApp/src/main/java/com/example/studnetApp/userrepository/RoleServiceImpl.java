package com.example.studnetApp.userrepository;

import com.example.studnetApp.exception.StudentException;
import com.example.studnetApp.userautho.Role;
import com.example.studnetApp.userautho.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public Role saveRole(Long userId, Role roleInput) throws StudentException{
        Optional<User> optional = userRepository.findById(userId);

        if(optional.isPresent()){
            optional.get().getRoles().add(roleInput);
            roleInput.setUser(optional.get());
            return roleRepository.save(roleInput);
        }
        else {
            throw new StudentException("User not found");
        }
    }



}
