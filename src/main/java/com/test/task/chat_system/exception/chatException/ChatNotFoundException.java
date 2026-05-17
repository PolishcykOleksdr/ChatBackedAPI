package com.test.task.chat_system.exception.chatException;

/**
 * author: user,
 * date: 17.05.2026
 */
public class ChatNotFoundException extends RuntimeException {
    public ChatNotFoundException(String message) {
        super(message);
    }
}
