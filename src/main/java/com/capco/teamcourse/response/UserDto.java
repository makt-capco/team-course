package com.capco.teamcourse.response;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserDto {

    private final Long id;
    private final String firstName;
    private final String lastName;
}
