package com.app.skillbox_laba4.mapper;

import com.app.skillbox_laba4.domain.User;
import com.app.skillbox_laba4.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto userDto);

    UserDto toDto(User user);

}
