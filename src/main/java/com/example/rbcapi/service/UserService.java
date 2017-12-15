package com.example.rbcapi.service;

import com.example.rbcapi.model.User;
import com.example.rbcapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    protected UserRepository userRepository;

    /**
     * Service called the userRepository to find all users
     *
     * @return List<User>
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Service called the userRepository to find the user by its id
     *
     * @param userId id of the user to find
     * @return User
     */
    public User getUser(Long userId) {
        return userRepository.findOne(userId);
    }

    /**
     * Service create a user by mapping userDTO to user and calling userService create method
     *
     * @param user user to create
     * @return User
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * @param userDetails details of the user to update
     */
    public User updateUser(User userDetails) {
        return userRepository.save(userDetails);
    }

    /**
     * Service called the repository to delete the user by its id
     *
     * @param userId user's id to delete
     */
    public void deleteUser(Long userId) {
        userRepository.delete(userId);
    }
}
