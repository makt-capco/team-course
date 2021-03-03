package com.capco.teamcourse.controller;

import com.capco.teamcourse.db.model.User;
import com.capco.teamcourse.db.repository.UserRepository;
import com.capco.teamcourse.request.NewUserRequest;
import com.capco.teamcourse.response.UserDto;
import com.capco.teamcourse.util.UserMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserController userController;

    @Test
    void createUser_whenNewUserRequestGivenThenItShouldCreateNewUser() {

        NewUserRequest newUserRequest = new NewUserRequest();
        newUserRequest.setFirstName("Mehmet");
        newUserRequest.setLastName("Aktas");

        User createdUser = mock(User.class);
        when(userRepository.insert(any(User.class))).thenReturn(createdUser);

        UserDto createdUserDto = mock(UserDto.class);
        when(userMapper.convertToUserDto(any(User.class))).thenReturn(createdUserDto);

        UserDto actual = userController.createUser(newUserRequest);

        assertThat(actual).isEqualTo(createdUserDto);

        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).insert(argumentCaptor.capture());

        assertThat(argumentCaptor.getValue().getFirstName()).isEqualTo(newUserRequest.getFirstName());
        assertThat(argumentCaptor.getValue().getLastName()).isEqualTo(newUserRequest.getLastName());

    }

    @Test
    void getUsers_whenCalledThenItShouldReturnUserList() {

        User userMock = mock(User.class);
        List<User> userList = Collections.nCopies(3, userMock);
        UserDto userDtoMock = mock(UserDto.class);
        List<UserDto> userDtoList = Collections.nCopies(3, userDtoMock);

        when(userRepository.findAll()).thenReturn(userList);
        when(userMapper.convertToUserDto(userMock)).thenReturn(userDtoMock);

        List<UserDto> actual = userController.getUsers();

        assertThat(actual).isEqualTo(userDtoList);
    }
}