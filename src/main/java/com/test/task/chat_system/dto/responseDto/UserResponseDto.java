package com.test.task.chat_system.dto.responseDto;

import java.time.LocalDateTime;

/**
 * author: user,
 * date: 18.05.2026
 */
public record UserResponseDto(
        Long id,
        String username,
        LocalDateTime createdAt
) {
}
