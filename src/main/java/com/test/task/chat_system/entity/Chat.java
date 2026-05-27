package com.test.task.chat_system.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;

import java.time.LocalDateTime;
import java.util.List;

/**
 * author: user,
 * date: 17.05.2026
 */

@Entity
@Table(name = "chats")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany
    private List<User> users;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
