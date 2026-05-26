package com.test.task.chat_system.service;

import com.test.task.chat_system.dto.requestDto.messageRequestDto.CreateMessageRequestDto;
import com.test.task.chat_system.dto.requestDto.messageRequestDto.GetChatMessagesRequestDto;
import com.test.task.chat_system.dto.responseDto.MessageResponseDto;
import com.test.task.chat_system.dto.responseDto.UserResponseDto;
import com.test.task.chat_system.entity.Chat;
import com.test.task.chat_system.entity.Message;
import com.test.task.chat_system.entity.User;
import com.test.task.chat_system.mapper.MessageMapper;
import com.test.task.chat_system.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private ChatService chatService;

    @Mock
    private UserService userService;

    @Mock
    private MessageMapper messageMapper;

    @InjectMocks
    private MessageService messageService;

    @Test
    void createMessageSavesMessageWithChatAuthorAndTextAndReturnsId() {
        CreateMessageRequestDto request = new CreateMessageRequestDto(10L, 20L, "Hello");
        Chat chat = new Chat().setId(10L).setName("Team");
        User author = user(20L, "alice");
        Message savedMessage = new Message();
        savedMessage.setId(30L);

        when(chatService.getChatById(10L)).thenReturn(chat);
        when(userService.getUserById(20L)).thenReturn(author);
        when(messageRepository.save(any(Message.class))).thenReturn(savedMessage);

        Long result = messageService.createMessage(request);

        assertEquals(30L, result);

        ArgumentCaptor<Message> messageCaptor = ArgumentCaptor.forClass(Message.class);
        verify(messageRepository).save(messageCaptor.capture());
        Message messageToSave = messageCaptor.getValue();
        assertSame(chat, messageToSave.getChat());
        assertSame(author, messageToSave.getAuthor());
        assertEquals("Hello", messageToSave.getText());
        verify(chatService).getChatById(10L);
        verify(userService).getUserById(20L);
    }

    @Test
    void getChatMessagesReturnsMappedMessagesFromRepository() {
        Long chatId = 10L;
        LocalDateTime createdAt = LocalDateTime.of(2026, 5, 26, 12, 30);
        Chat chat = new Chat().setId(chatId).setName("Team");
        User author = user(20L, "alice");
        Message message = new Message();
        message.setId(30L);
        message.setChat(chat);
        message.setAuthor(author);
        message.setText("Hello");
        message.setCreatedAt(createdAt);
        List<Message> messages = List.of(message);
        List<MessageResponseDto> response = List.of(
                new MessageResponseDto(
                        30L,
                        chatId,
                        new UserResponseDto(20L, "alice", createdAt),
                        "Hello",
                        createdAt
                )
        );

        when(messageRepository.findAllByChat_IdOrderByCreatedAtAsc(chatId)).thenReturn(messages);
        when(messageMapper.toDtoList(messages)).thenReturn(response);

        List<MessageResponseDto> result = messageService.getChatMessages(new GetChatMessagesRequestDto(chatId));

        assertSame(response, result);
        verify(messageRepository).findAllByChat_IdOrderByCreatedAtAsc(chatId);
        verify(messageMapper).toDtoList(messages);
    }

    @Test
    void getChatMessagesReturnsEmptyListWhenRepositoryFindsNoMessages() {
        Long chatId = 10L;
        List<Message> messages = List.of();
        List<MessageResponseDto> response = List.of();

        when(messageRepository.findAllByChat_IdOrderByCreatedAtAsc(chatId)).thenReturn(messages);
        when(messageMapper.toDtoList(messages)).thenReturn(response);

        List<MessageResponseDto> result = messageService.getChatMessages(new GetChatMessagesRequestDto(chatId));

        assertSame(response, result);
        verify(messageRepository).findAllByChat_IdOrderByCreatedAtAsc(chatId);
        verify(messageMapper).toDtoList(messages);
    }

    private User user(Long id, String username) {
        User user = new User();
        user.setId(id);
        user.setUserName(username);
        return user;
    }
}
