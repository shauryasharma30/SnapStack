package com.shaurya.SnapStack.service;

import com.shaurya.SnapStack.model.User;
import com.shaurya.SnapStack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User login(User user) {
        User existingUser = userRepository.checkCredentials(user.getUsername(), user.getPassword());
        if(existingUser == null) {
            return null;
        }
        else {
            return existingUser;
        }
    }

    public void signupUser(User newUser) {
        userRepository.signupUser(newUser);
    }
}
