package com.test.task.chat_system.controller;

import com.test.task.chat_system.dto.requestDto.chatRequestDto.CreateChatRequestDto;
import com.test.task.chat_system.dto.requestDto.chatRequestDto.GetUserChatsRequestDto;
import com.test.task.chat_system.dto.responseDto.ChatResponseDto;
import com.test.task.chat_system.service.ChatService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author: user,
 * date: 17.05.2026
 */

@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Long addChat(
            @NotNull @Valid @RequestBody CreateChatRequestDto chatRequestDto
    ) {
        log.info(
                "Received request to create chat with name {} and {} users",
                chatRequestDto.name(),
                chatRequestDto.userIds().size()
        );
        return chatService.createChat(chatRequestDto);
    }

    @PostMapping("/get")
    @ResponseStatus(code = HttpStatus.OK)
    public List<ChatResponseDto> getUserChats(
            @NotNull @Valid @RequestBody GetUserChatsRequestDto getUserChatsRequestDto
    ) {
        log.info("Received request to get chats for user id {}", getUserChatsRequestDto.userId());
        return chatService.getChatsByUserId(getUserChatsRequestDto);
    }
}
