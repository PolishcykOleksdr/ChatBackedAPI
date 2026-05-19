package com.test.task.chat_system.controller;

import com.test.task.chat_system.dto.requestDto.messageRequestDto.CreateMessageRequestDto;
import com.test.task.chat_system.dto.requestDto.messageRequestDto.GetChatMessagesRequestDto;
import com.test.task.chat_system.dto.responseDto.MessageResponseDto;
import com.test.task.chat_system.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author: user,
 * date: 17.05.2026
 */

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/add")
    public ResponseEntity<Long> addMessage(
            @Valid @RequestBody CreateMessageRequestDto createMessageRequestDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(messageService.createMessage(createMessageRequestDto));
    }

    @PostMapping("/get")
    public ResponseEntity<List<MessageResponseDto>> getChatMessages(
            @Valid @RequestBody GetChatMessagesRequestDto getChatMessagesRequestDto
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(messageService.getChatMessages(getChatMessagesRequestDto));
    }
}
