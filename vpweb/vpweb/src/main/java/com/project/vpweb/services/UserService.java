package com.project.vpweb.services;

import com.project.vpweb.models.UserModel;
import com.project.vpweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(UserModel userModel) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userRepository.save(userModel);
    }

    public UserModel getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }
    public UserModel getUserByUsername(String username) {
        return getAllUsers().stream().filter(user -> user.getUserName().equals(username)).findFirst().orElse(null);
    }

    public UserModel saveCategory(UserModel userModel) {
        return userRepository.save(userModel);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
