package com.test.task.chat_system.service;

import com.test.task.chat_system.dto.requestDto.messageRequestDto.CreateMessageRequestDto;
import com.test.task.chat_system.dto.requestDto.messageRequestDto.GetChatMessagesRequestDto;
import com.test.task.chat_system.dto.responseDto.MessageResponseDto;
import com.test.task.chat_system.entity.Message;
import com.test.task.chat_system.mapper.MessageMapper;
import com.test.task.chat_system.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: user,
 * date: 17.05.2026
 */

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatService chatService;
    private final UserService userService;
    private final MessageMapper messageMapper;

    public Long createMessage(CreateMessageRequestDto createMessageRequestDto){
        Message message = new Message();
        message.setChat(chatService.getChatById(createMessageRequestDto.chatId()));
        message.setAuthor(userService.getUserById(createMessageRequestDto.userId()));
        message.setText(createMessageRequestDto.text());

        return messageRepository.save(message).getId();
    }

    public List<MessageResponseDto> getChatMessages(GetChatMessagesRequestDto getChatMessagesRequestDto) {
        return messageMapper.toDtoList(messageRepository.findAllByChat_IdOrderByCreatedAtAsc(
                getChatMessagesRequestDto.chatId()
        ));
    }
}
