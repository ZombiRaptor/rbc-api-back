package com.example.rbcapi.controller;

import com.example.rbcapi.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserController {
    List<UserDTO> getAllUsers();

    ResponseEntity<UserDTO> getUser(Long userId);

    UserDTO createUser(UserDTO userDTO);

    ResponseEntity<UserDTO> updateUser(Long userId, UserDTO userDetailsDTO);

    ResponseEntity<UserDTO> deleteUser(Long userId);
}
