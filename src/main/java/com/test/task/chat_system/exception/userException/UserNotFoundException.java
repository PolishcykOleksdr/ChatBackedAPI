package com.test.task.chat_system.exception.userException;

/**
 * author: user,
 * date: 17.05.2026
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
