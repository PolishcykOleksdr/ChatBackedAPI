package com.test.task.chat_system.repository;

import com.test.task.chat_system.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author: user,
 * date: 17.05.2026
 */

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByChat_IdOrderByCreatedAtAsc(Long chatId);
}