package com.dpaywallet.services;

import com.dpaywallet.models.User;
import com.dpaywallet.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserByNationalId(String nationalId) {
        return userRepository.findByNationalId(nationalId);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
}
