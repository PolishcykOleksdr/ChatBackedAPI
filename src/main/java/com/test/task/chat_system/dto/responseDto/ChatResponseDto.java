package com.test.task.chat_system.dto.responseDto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * author: user,
 * date: 18.05.2026
 */

public record ChatResponseDto(
        Long id,
        String name,
        List<UserResponseDto> users,
        LocalDateTime createdAt
) {
}