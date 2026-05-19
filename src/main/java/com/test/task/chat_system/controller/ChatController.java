package com.test.task.chat_system.controller;

import com.test.task.chat_system.dto.requestDto.chatRequestDto.CreateChatRequestDto;
import com.test.task.chat_system.dto.requestDto.chatRequestDto.GetUserChatsRequestDto;
import com.test.task.chat_system.dto.responseDto.ChatResponseDto;
import com.test.task.chat_system.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Long> addChat(
            @Valid @RequestBody CreateChatRequestDto chatRequestDto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chatService.createChat(chatRequestDto));
    }

    @PostMapping("/get")
    public ResponseEntity<List<ChatResponseDto>> getUserChats(
            @Valid @RequestBody GetUserChatsRequestDto getUserChatsRequestDto
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(chatService.getChatsByUserId(getUserChatsRequestDto));
    }
}
