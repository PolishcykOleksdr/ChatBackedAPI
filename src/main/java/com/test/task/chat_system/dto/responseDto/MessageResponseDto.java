package com.test.task.chat_system.dto.responseDto;

import java.time.LocalDateTime;

/**
 * author: user,
 * date: 18.05.2026
 */

public record MessageResponseDto(
        Long id,
        Long chatId,
        UserResponseDto author,
        String text,
        LocalDateTime createdAt
) {
}
