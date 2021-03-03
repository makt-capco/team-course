package com.capco.teamcourse.util;

import com.capco.teamcourse.db.model.User;
import com.capco.teamcourse.response.UserDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserDto convertToUserDto(User user);
}
