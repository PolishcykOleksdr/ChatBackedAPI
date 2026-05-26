package com.test.task.chat_system.service;

import com.test.task.chat_system.dto.requestDto.userRequestDto.CreateUserRequestDto;
import com.test.task.chat_system.entity.User;
import com.test.task.chat_system.exception.userException.UserNotFoundException;
import com.test.task.chat_system.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void addUserSavesUsernameAndReturnsId() {
        CreateUserRequestDto request = new CreateUserRequestDto("alice");
        User savedUser = new User();
        savedUser.setId(7L);

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        Long result = userService.addUser(request);

        assertEquals(7L, result);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());
        assertEquals("alice", userCaptor.getValue().getUserName());
    }

    @Test
    void getUserByIdReturnsUserWhenExists() {
        User user = user(1L, "alice");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertSame(user, result);
        verify(userRepository).findById(1L);
    }

    @Test
    void getUserByIdThrowsWhenUserDoesNotExist() {
        when(userRepository.findById(42L)).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(
                UserNotFoundException.class,
                () -> userService.getUserById(42L)
        );

        assertEquals("User with id 42 not found", exception.getMessage());
        verify(userRepository).findById(42L);
    }

    @Test
    @SuppressWarnings("unchecked")
    void getUsersByIdsDeduplicatesIdsBeforeRepositoryLookup() {
        List<Long> ids = List.of(1L, 1L, 2L);
        List<User> users = List.of(user(1L, "alice"), user(2L, "bob"));

        when(userRepository.findAllById(any())).thenReturn(users);

        List<User> result = userService.getUsersByIds(ids);

        assertSame(users, result);

        ArgumentCaptor<Iterable<Long>> idsCaptor = ArgumentCaptor.forClass(Iterable.class);
        verify(userRepository).findAllById(idsCaptor.capture());
        List<Long> capturedIds = new ArrayList<>();
        idsCaptor.getValue().forEach(capturedIds::add);
        assertEquals(Set.of(1L, 2L), new HashSet<>(capturedIds));
        assertEquals(2, capturedIds.size());
    }

    private User user(Long id, String username) {
        User user = new User();
        user.setId(id);
        user.setUserName(username);
        return user;
    }
}
