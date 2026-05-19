package com.test.task.chat_system.mapper;

import com.test.task.chat_system.dto.responseDto.UserResponseDto;
import com.test.task.chat_system.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * author: user,
 * date: 18.05.2026
 */

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "username", source = "userName")
    UserResponseDto toDto(User user);

    @Mapping(target = "userName", source = "username")
    User toEntity(UserResponseDto userResponseDto);

    List<UserResponseDto> toDtoList(List<User> users);
}
