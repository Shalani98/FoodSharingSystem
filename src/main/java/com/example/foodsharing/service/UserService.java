package com.example.foodsharing.service;

import com.example.foodsharing.entity.User;
import com.example.foodsharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String register(User user) {
        String hashedPassword=passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return "User Registered Successfuly";
    }

    public String login(User user) {
        User pastUser=userRepository.findByEmail(user.getEmail());
        if(pastUser==null){
            return "User not found";
        }
        if(!passwordEncoder.matches(user.getPassword(),pastUser.getPassword())){
            return "Invalid Password";
        }
        return pastUser.getRole();
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
