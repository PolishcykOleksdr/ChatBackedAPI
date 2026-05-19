package com.test.task.chat_system.mapper;

import com.test.task.chat_system.dto.responseDto.ChatResponseDto;
import com.test.task.chat_system.entity.Chat;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * author: user,
 * date: 18.05.2026
 */

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface ChatMapper {
    ChatResponseDto toDto(Chat chat);

    Chat toEntity(ChatResponseDto chatResponseDto);

    List<ChatResponseDto> toDtoList(List<Chat> chats);
}
