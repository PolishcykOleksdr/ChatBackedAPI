package com.test.task.chat_system.dto.requestDto.userRequestDto;

import com.test.task.chat_system.annotation.MaxLength;
import com.test.task.chat_system.annotation.MinLength;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

/**
 * author: user,
 * date: 17.05.2026
 */

public record CreateUserRequestDto(
        @NotNull
        @MinLength(min = 4)
        @MaxLength(max = 15)
        @JsonProperty("username")
        String userName
) {
}
