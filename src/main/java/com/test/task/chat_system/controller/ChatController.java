package com.test.task.chat_system.controller;

import com.test.task.chat_system.dto.requestDto.chatRequestDto.CreateChatRequestDto;
import com.test.task.chat_system.dto.requestDto.chatRequestDto.GetUserChatsRequestDto;
import com.test.task.chat_system.dto.responseDto.ChatResponseDto;
import com.test.task.chat_system.service.ChatService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
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
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Long addChat(
            @NotNull @Valid @RequestBody CreateChatRequestDto chatRequestDto
    ) {
        return chatService.createChat(chatRequestDto);
    }

    @PostMapping("/get")
    @ResponseStatus(code = HttpStatus.OK)
    public List<ChatResponseDto> getUserChats(
            @NotNull @Valid @RequestBody GetUserChatsRequestDto getUserChatsRequestDto
    ) {
        return chatService.getChatsByUserId(getUserChatsRequestDto);
    }
}
