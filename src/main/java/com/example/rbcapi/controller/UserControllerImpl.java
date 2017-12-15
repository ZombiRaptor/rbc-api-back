package com.example.rbcapi.controller;

import com.example.rbcapi.dto.UserDTO;
import com.example.rbcapi.model.User;
import com.example.rbcapi.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserControllerImpl implements UserController {

    @Autowired
    UserService userService;

    /**
     * Controller called the userService to find all users and returning the list of userDTO
     * @return List<UserDTO>
     */
    @Override
    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        ModelMapper modelMapper = new ModelMapper();
        List<User> users = userService.getUsers();
        return users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    /**
     * Controller called the userService to find the user by its id and returning its DTO
     * @param userId
     * @return ResponseEntity<UserDTO>
     */
    @Override
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable(value = "id") Long userId) {
        User user = userService.getUser(userId);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        ModelMapper modelMapper = new ModelMapper();

        return ResponseEntity.ok().body(modelMapper.map(user, UserDTO.class));
    }

    /**
     * Controller create a user by mapping userDTO to user and calling userService create method
     *
     * @param UserDTO user to create
     * @return UserDTO
     */
    @Override
    @PostMapping("/users")
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        ModelMapper modelMapper = new ModelMapper();
        User user = userService.createUser(modelMapper.map(userDTO, User.class));

        return modelMapper.map(user, UserDTO.class);
    }

    /**
     * Call the service to update the user
     *
     * @param userId
     * @param userDetailsDTO details of the user to update
     */
    @Override
    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable(value = "id") Long userId, @Valid @RequestBody UserDTO userDetailsDTO) {
        User user = userService.getUser(userId);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        ModelMapper modelMapper = new ModelMapper();
        User updatedUser = userService.updateUser(modelMapper.map(userDetailsDTO, User.class));

        return ResponseEntity.ok(modelMapper.map(updatedUser, UserDTO.class));
    }

    /**
     * Controller called the service to delete the user by its id
     *
     * @param userId user's id to delete
     */
    @Override
    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable(value = "id") Long userId) {
        User user = userService.getUser(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        userService.deleteUser(user.getId());

        return ResponseEntity.ok().build();
    }
}
