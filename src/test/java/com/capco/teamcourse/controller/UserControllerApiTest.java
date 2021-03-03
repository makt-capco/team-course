package com.capco.teamcourse.controller;


import com.capco.teamcourse.db.model.User;
import com.capco.teamcourse.db.repository.UserRepository;
import com.capco.teamcourse.request.NewUserRequest;
import com.capco.teamcourse.response.UserDto;
import com.capco.teamcourse.util.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
class UserControllerApiTest {

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserMapper userMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createUser_whenNewUserRequestSentThenItShouldCreateNewUser() throws Exception {

        User newUser = new User();
        newUser.setFirstName("Mehmet");
        newUser.setLastName("Aktas");

        when(userRepository.insert(any(User.class))).thenReturn(newUser);

        UserDto userDto = new UserDto(1L, newUser.getFirstName(), newUser.getLastName());
        when(userMapper.convertToUserDto(newUser)).thenReturn(userDto);

        mockMvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON).content("{\"firstName\": \"Mehmet\",\n" +
                "\"lastName\": \"Aktas\"}")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.firstName", is("Mehmet")));
    }

    @Test
    void getUsers_whenCalledThenItShouldReturnUserList() throws Exception {

        User newUser = new User();
        newUser.setFirstName("Mehmet");
        newUser.setLastName("Aktas");

        List<User> userList = Collections.nCopies(1, newUser);

        UserDto userDtoMock = new UserDto(1L, newUser.getFirstName(), newUser.getLastName());

        List<UserDto> userDtoList = Collections.nCopies(1, userDtoMock);

        when(userRepository.findAll()).thenReturn(userList);
        when(userMapper.convertToUserDto(newUser)).thenReturn(userDtoMock);

        mockMvc.perform(get("/users/")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[0].firstName", is("Mehmet")));
    }
}