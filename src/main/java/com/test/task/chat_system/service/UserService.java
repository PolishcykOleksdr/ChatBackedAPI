package com.test.task.chat_system.service;

import com.test.task.chat_system.dto.requestDto.userRequestDto.CreateUserRequestDto;
import com.test.task.chat_system.entity.User;
import com.test.task.chat_system.exception.userException.UserNotFoundException;
import com.test.task.chat_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * author: user,
 * date: 17.05.2026
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public Long addUser(CreateUserRequestDto createUserRequestDto){
        User user = new User();
        user.setUserName(createUserRequestDto.userName());

        Long userId = userRepository.save(user).getId();
        log.info("Created user with id {} and username {}", userId, createUserRequestDto.userName());
        return userId;
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> {
            log.warn("User with id {} not found", id);
            return new UserNotFoundException(String.format("User with id %d not found", id));
        });
    }

    public List<User> getUsersByIds(List<Long> ids){
        Set<Long> uniqueIds = new HashSet<>(ids);

        List<User> users = userRepository.findAllById(uniqueIds);
        if (users.size() != uniqueIds.size()) {
            log.warn("Requested {} unique users, but found {}", uniqueIds.size(), users.size());
        }

        log.info("Found {} users by their ids", users.size());
        return users;
    }
}
