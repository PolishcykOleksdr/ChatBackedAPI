package com.test.task.chat_system.repository;

import com.test.task.chat_system.entity.Chat;
import com.test.task.chat_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author: user,
 * date: 17.05.2026
 */

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query("""
        SELECT c FROM Chat c
        JOIN c.users u
        LEFT JOIN Message m on m.chat = c
        WHERE u.id = :userId
        GROUP BY c.id
        ORDER BY MAX(m.createdAt) DESC NULLS LAST, c.createdAt DESC
        """)
    List<Chat> findAllByUserOrderByLastMessage(@Param("userId") Long userId);
}