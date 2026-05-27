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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * author: user,
 * date: 17.05.2026
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserService userService;
    private final ChatMapper chatMapper;

    @Transactional
    public Long createChat(CreateChatRequestDto createChatRequestDto){
        Chat chat = new Chat();
        chat.setName(createChatRequestDto.name());
        List<User> users = userService.getUsersByIds(createChatRequestDto.userIds());
        if (users.size() != createChatRequestDto.userIds().size()) {
            log.warn(
                    "Requested {} users for chat {}, but found {}",
                    createChatRequestDto.userIds().size(),
                    createChatRequestDto.name(),
                    users.size()
            );
        }
        chat.setUsers(users);

        Long chatId = chatRepository.save(chat).getId();
        log.info("Created chat with id {} and name {}", chatId, createChatRequestDto.name());
        return chatId;
    }

    public Chat getChatById(Long id){
        return chatRepository.findById(id).orElseThrow(() -> {
            log.warn("Chat with id {} not found", id);
            return new ChatNotFoundException(String.format("Chat with id %d not found", id));
        });
    }

    @Transactional(readOnly = true)
    public List<ChatResponseDto> getChatsByUserId(GetUserChatsRequestDto getUserChatsRequestDto){
        List<ChatResponseDto> chats = chatMapper.toDtoList(
                chatRepository.findAllByUserOrderByLastMessage(getUserChatsRequestDto.userId())
        );
        log.info("Found {} chats for user id {}", chats.size(), getUserChatsRequestDto.userId());
        return chats;
    }
}
