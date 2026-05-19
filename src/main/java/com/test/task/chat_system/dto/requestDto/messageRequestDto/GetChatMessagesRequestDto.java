package com.test.task.chat_system.dto.requestDto.messageRequestDto;

import com.test.task.chat_system.annotation.ValidId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

/**
 * author: user,
 * date: 17.05.2026
 */
public record GetChatMessagesRequestDto(
        @NotNull
        @ValidId
        @JsonProperty("chat")
        Long chatId
) {
}
