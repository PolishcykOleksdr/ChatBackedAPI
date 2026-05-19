package com.test.task.chat_system.dto.requestDto.chatRequestDto;

import com.test.task.chat_system.annotation.MaxLength;
import com.test.task.chat_system.annotation.MinLength;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * author: user,
 * date: 17.05.2026
 */

public record CreateChatRequestDto(
        @NotNull
        @MinLength(min = 4)
        @MaxLength(max = 15)
        String name,
        @NotNull
        @NotEmpty
        @JsonProperty("users")
        List<Long> userIds
) {
}
