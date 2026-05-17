package com.test.task.chat_system.service;

import com.test.task.chat_system.dto.requestDto.messageRequestDto.CreateMessageRequestDto;
import com.test.task.chat_system.dto.requestDto.messageRequestDto.GetChatMessagesRequestDto;
import com.test.task.chat_system.entity.Message;
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

    public Long createMessage(CreateMessageRequestDto createMessageRequestDto){
        Message message = new Message();
        message.setChat(chatService.getChatById(createMessageRequestDto.chatId()));
        message.setAuthor(userService.getUserById(createMessageRequestDto.userId()));
        message.setText(createMessageRequestDto.text());

        return messageRepository.save(message).getId();
    }

    public List<Message> getChatMessages(GetChatMessagesRequestDto getChatMessagesRequestDto) {
        return messageRepository.findAllByChat_IdOrderByCreatedAtAsc(
                getChatMessagesRequestDto.chatId()
        );
    }
}