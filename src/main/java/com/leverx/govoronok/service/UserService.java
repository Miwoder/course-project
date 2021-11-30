package com.leverx.govoronok.service;

import com.leverx.govoronok.model.Role;
import com.leverx.govoronok.model.User;
import com.leverx.govoronok.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void addNewUser(User user){
        userRepository.save(user);
    }

    public List<User> getAllUnconfirmedUsers(Role traderRole){
        return userRepository.getUsersByApprovedIsTrueAndConfirmedByAdminFalseAndRoleIs(traderRole);
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }
}
