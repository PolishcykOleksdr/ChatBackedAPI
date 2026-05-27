package com.test.task.chat_system.service;

import com.test.task.chat_system.dto.requestDto.messageRequestDto.CreateMessageRequestDto;
import com.test.task.chat_system.dto.requestDto.messageRequestDto.GetChatMessagesRequestDto;
import com.test.task.chat_system.dto.responseDto.MessageResponseDto;
import com.test.task.chat_system.entity.Message;
import com.test.task.chat_system.mapper.MessageMapper;
import com.test.task.chat_system.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: user,
 * date: 17.05.2026
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatService chatService;
    private final UserService userService;
    private final MessageMapper messageMapper;

    public Long createMessage(CreateMessageRequestDto createMessageRequestDto){
        log.info(
                "Creating message in chat id {} by user id {}",
                createMessageRequestDto.chatId(),
                createMessageRequestDto.userId()
        );
        Message message = new Message();
        message.setChat(chatService.getChatById(createMessageRequestDto.chatId()));
        message.setAuthor(userService.getUserById(createMessageRequestDto.userId()));
        message.setText(createMessageRequestDto.text());

        Long messageId = messageRepository.save(message).getId();
        log.info(
                "Created message with id {} in chat id {} by user id {}",
                messageId,
                createMessageRequestDto.chatId(),
                createMessageRequestDto.userId()
        );
        return messageId;
    }

    public List<MessageResponseDto> getChatMessages(GetChatMessagesRequestDto getChatMessagesRequestDto) {
        List<MessageResponseDto> messages = messageMapper.toDtoList(
                messageRepository.findAllByChat_IdOrderByCreatedAtAsc(getChatMessagesRequestDto.chatId())
        );
        log.info("Found {} messages for chat id {}", messages.size(), getChatMessagesRequestDto.chatId());
        return messages;
    }
}
