package com.leverx.govoronok.service;

import com.leverx.govoronok.repository.GameObjectRepository;
import com.leverx.govoronok.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
}
