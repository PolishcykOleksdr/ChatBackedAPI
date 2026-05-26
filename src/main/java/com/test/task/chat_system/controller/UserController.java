package com.test.task.chat_system.controller;

import com.test.task.chat_system.dto.requestDto.userRequestDto.CreateUserRequestDto;
import com.test.task.chat_system.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * author: user,
 * date: 17.05.2026
 */

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Long addUser(
            @NotNull  @Valid @RequestBody CreateUserRequestDto createUserRequestDto
    ) {
        return userService.addUser(createUserRequestDto);
    }
}
