package com.test.task.chat_system.service;

import com.test.task.chat_system.dto.requestDto.chatRequestDto.CreateChatRequestDto;
import com.test.task.chat_system.dto.requestDto.chatRequestDto.GetUserChatsRequestDto;
import com.test.task.chat_system.dto.responseDto.ChatResponseDto;
import com.test.task.chat_system.entity.Chat;
import com.test.task.chat_system.entity.User;
import com.test.task.chat_system.exception.chatException.ChatNotFoundException;
import com.test.task.chat_system.mapper.ChatMapper;
import com.test.task.chat_system.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * author: user,
 * date: 17.05.2026
 */

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserService userService;
    private final ChatMapper chatMapper;

    @Transactional
    public Long createChat(CreateChatRequestDto createChatRequestDto){
        Chat chat = new Chat();
        chat.setName(createChatRequestDto.name());
        List<User> users = userService.getUsersByIds(createChatRequestDto.userIds());
        chat.setUsers(users);

        return chatRepository.save(chat).getId();
    }

    public Chat getChatById(Long id){
        return chatRepository.findById(id).orElseThrow(() ->
                new ChatNotFoundException(String.format("Chat with id %d not found", id))
        );
    }

    public List<ChatResponseDto> getChatsByUserId(GetUserChatsRequestDto getUserChatsRequestDto){
        return chatMapper.toDtoList(chatRepository.findAllByUserOrderByLastMessage(getUserChatsRequestDto.userId()));
    }
}
