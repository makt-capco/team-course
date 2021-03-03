package com.capco.teamcourse.controller;


import com.capco.teamcourse.db.model.User;
import com.capco.teamcourse.db.repository.UserRepository;
import com.capco.teamcourse.exception.NotFoundException;
import com.capco.teamcourse.request.NewUserRequest;
import com.capco.teamcourse.response.HelloMessageDto;
import com.capco.teamcourse.response.UserDto;
import com.capco.teamcourse.util.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger LOGER = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserRepository userRepository, UserMapper userMapper) {

        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createUser(@Valid @RequestBody NewUserRequest newUserRequest) {

        User newUser = new User();

        newUser.setFirstName(newUserRequest.getFirstName());
        newUser.setLastName(newUserRequest.getLastName());

        User createdUser = userRepository.insert(newUser);

        return userMapper.convertToUserDto(createdUser);

    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getUsers() {

        List<User> userList = userRepository.findAll();

        return userList.stream().map(userMapper::convertToUserDto).collect(Collectors.toList());

    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto getUser(@PathVariable("id") Long id) {

        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User not found")
        );

        return userMapper.convertToUserDto(user);
    }


}
