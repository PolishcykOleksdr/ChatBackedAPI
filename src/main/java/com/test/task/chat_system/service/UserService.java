package com.test.task.chat_system.service;

import com.test.task.chat_system.dto.requestDto.userRequestDto.CreateUserRequestDto;
import com.test.task.chat_system.entity.User;
import com.test.task.chat_system.exception.userException.UserNotFoundException;
import com.test.task.chat_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
public class UserService {
    private final UserRepository userRepository;

    public Long addUser(CreateUserRequestDto createUserRequestDto){
        User user = new User();
        user.setUserName(createUserRequestDto.userName());

        return userRepository.save(user).getId();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(String.format("User with id %d not found", id))
        );
    }

    public List<User> getUsersByIds(List<Long> ids){
        List<User> users = userRepository.findAllById(ids);
        Set<Long> uniqueIds = new HashSet<>(ids);

        if (users.size() != uniqueIds.size()) {
            throw new UserNotFoundException("One or more users were not found");
        }

        return users;
    }
}
