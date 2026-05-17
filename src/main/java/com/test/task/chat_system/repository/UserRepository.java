package com.test.task.chat_system.repository;

import com.test.task.chat_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * author: user,
 * date: 17.05.2026
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> { }