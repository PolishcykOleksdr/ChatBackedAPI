package com.test.task.chat_system.controller;

import com.test.task.chat_system.dto.requestDto.messageRequestDto.CreateMessageRequestDto;
import com.test.task.chat_system.dto.requestDto.messageRequestDto.GetChatMessagesRequestDto;
import com.test.task.chat_system.dto.responseDto.MessageResponseDto;
import com.test.task.chat_system.service.MessageService;
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
@RequestMapping("/messages")
@RequiredArgsConstructor
@Slf4j
public class MessageController {
    private final MessageService messageService;

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Long addMessage(
            @NotNull  @Valid @RequestBody CreateMessageRequestDto createMessageRequestDto
    ) {
        log.info(
                "Received request to create message in chat id {} by user id {}",
                createMessageRequestDto.chatId(),
                createMessageRequestDto.userId()
        );
        return messageService.createMessage(createMessageRequestDto);
    }

    @PostMapping("/get")
    @ResponseStatus(code = HttpStatus.OK)
    public List<MessageResponseDto> getChatMessages(
            @NotNull @Valid @RequestBody GetChatMessagesRequestDto getChatMessagesRequestDto
    ) {
        log.info("Received request to get messages for chat id {}", getChatMessagesRequestDto.chatId());
        return messageService.getChatMessages(getChatMessagesRequestDto);
    }
}
