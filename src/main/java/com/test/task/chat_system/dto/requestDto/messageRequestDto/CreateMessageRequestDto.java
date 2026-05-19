package com.test.task.chat_system.dto.requestDto.messageRequestDto;

import com.test.task.chat_system.annotation.MaxLength;
import com.test.task.chat_system.annotation.MinLength;
import com.test.task.chat_system.annotation.ValidId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

/**
 * author: user,
 * date: 17.05.2026
 */
public record CreateMessageRequestDto(
        @NotNull
        @ValidId
        @JsonProperty("chat")
        Long chatId,
        @NotNull
        @ValidId
        @JsonProperty("author")
        Long userId,
        @NotNull
        @MinLength(min = 1)
        @MaxLength(max = 300)
        String text
) {
}
