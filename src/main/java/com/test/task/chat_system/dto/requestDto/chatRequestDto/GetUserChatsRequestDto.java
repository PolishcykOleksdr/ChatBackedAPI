package com.test.task.chat_system.dto.requestDto.chatRequestDto;

import com.test.task.chat_system.annotation.ValidId;
import jakarta.validation.constraints.NotNull;

/**
 * author: user,
 * date: 17.05.2026
 */
public record GetUserChatsRequestDto(
        @NotNull
        @ValidId
        Long userId
) {
}
