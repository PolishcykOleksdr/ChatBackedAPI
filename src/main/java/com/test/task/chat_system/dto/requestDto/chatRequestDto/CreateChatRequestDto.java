package com.test.task.chat_system.dto.requestDto.chatRequestDto;

import com.test.task.chat_system.annotation.MaxLength;
import com.test.task.chat_system.annotation.MinLength;
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
        List<Long> userIds
) {
}