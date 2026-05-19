package com.test.task.chat_system.mapper;

import com.test.task.chat_system.dto.responseDto.MessageResponseDto;
import com.test.task.chat_system.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * author: user,
 * date: 18.05.2026
 */

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface MessageMapper {
    @Mapping(target = "chatId", source = "chat.id")
    MessageResponseDto toDto(Message message);

    @Mapping(target = "chat.id", source = "chatId")
    Message toEntity(MessageResponseDto messageResponseDto);

    List<MessageResponseDto> toDtoList(List<Message> messages);
}
