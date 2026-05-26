package com.test.task.chat_system.service;

import com.test.task.chat_system.dto.requestDto.chatRequestDto.CreateChatRequestDto;
import com.test.task.chat_system.dto.requestDto.chatRequestDto.GetUserChatsRequestDto;
import com.test.task.chat_system.dto.responseDto.ChatResponseDto;
import com.test.task.chat_system.dto.responseDto.UserResponseDto;
import com.test.task.chat_system.entity.Chat;
import com.test.task.chat_system.entity.User;
import com.test.task.chat_system.exception.chatException.ChatNotFoundException;
import com.test.task.chat_system.mapper.ChatMapper;
import com.test.task.chat_system.repository.ChatRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChatServiceTest {

    @Mock
    private ChatRepository chatRepository;

    @Mock
    private UserService userService;

    @Mock
    private ChatMapper chatMapper;

    @InjectMocks
    private ChatService chatService;

    @Test
    void createChatSavesChatWithNameAndUsersAndReturnsId() {
        List<Long> userIds = List.of(1L, 2L);
        CreateChatRequestDto request = new CreateChatRequestDto("Team", userIds);
        List<User> users = List.of(user(1L, "alice"), user(2L, "bob"));
        Chat savedChat = new Chat().setId(100L);

        when(userService.getUsersByIds(userIds)).thenReturn(users);
        when(chatRepository.save(any(Chat.class))).thenReturn(savedChat);

        Long result = chatService.createChat(request);

        assertEquals(100L, result);

        ArgumentCaptor<Chat> chatCaptor = ArgumentCaptor.forClass(Chat.class);
        verify(chatRepository).save(chatCaptor.capture());

        Chat chatToSave = chatCaptor.getValue();

        assertEquals("Team", chatToSave.getName());
        assertSame(users, chatToSave.getUsers());

        verify(userService).getUsersByIds(userIds);
    }

    @Test
    void getChatByIdReturnsChatWhenExists() {
        Chat chat = new Chat().setId(1L).setName("Team");

        when(chatRepository.findById(1L)).thenReturn(Optional.of(chat));

        Chat result = chatService.getChatById(1L);

        assertSame(chat, result);
        verify(chatRepository).findById(1L);
    }

    @Test
    void getChatByIdThrowsWhenChatDoesNotExist() {
        when(chatRepository.findById(99L)).thenReturn(Optional.empty());

        ChatNotFoundException exception = assertThrows(
                ChatNotFoundException.class,
                () -> chatService.getChatById(99L)
        );

        assertEquals("Chat with id 99 not found", exception.getMessage());
        verify(chatRepository).findById(99L);
    }

    @Test
    void getChatsByUserIdReturnsMappedChatsOrderedByRepository() {
        Long userId = 1L;
        User user = user(userId, "alice");
        LocalDateTime createdAt = LocalDateTime.of(2026, 5, 26, 10, 0);
        Chat chat = new Chat()
                .setId(10L)
                .setName("Team")
                .setCreatedAt(createdAt)
                .setUsers(List.of(user));
        List<Chat> chats = List.of(chat);
        List<ChatResponseDto> response = List.of(
                new ChatResponseDto(
                        10L,
                        "Team",
                        List.of(new UserResponseDto(userId, "alice", createdAt)),
                        createdAt
                )
        );

        when(chatRepository.findAllByUserOrderByLastMessage(userId)).thenReturn(chats);
        when(chatMapper.toDtoList(chats)).thenReturn(response);

        List<ChatResponseDto> result = chatService.getChatsByUserId(new GetUserChatsRequestDto(userId));

        assertSame(response, result);
        verify(chatRepository).findAllByUserOrderByLastMessage(userId);
        verify(chatMapper).toDtoList(chats);
    }

    @Test
    void getChatsByUserIdReturnsEmptyListWhenRepositoryFindsNoChats() {
        Long userId = 1L;
        List<Chat> chats = List.of();
        List<ChatResponseDto> response = List.of();

        when(chatRepository.findAllByUserOrderByLastMessage(userId)).thenReturn(chats);
        when(chatMapper.toDtoList(chats)).thenReturn(response);

        List<ChatResponseDto> result = chatService.getChatsByUserId(new GetUserChatsRequestDto(userId));

        assertSame(response, result);
        verify(chatRepository).findAllByUserOrderByLastMessage(userId);
        verify(chatMapper).toDtoList(chats);
        verifyNoInteractions(userService);
    }

    private User user(Long id, String username) {
        User user = new User();
        user.setId(id);
        user.setUserName(username);
        return user;
    }
}
