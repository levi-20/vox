package com.cs.vox.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(nullable = false, name = "room_id")
    private Room room;

    @Column(nullable = false, name = "message")
    private String content;

    @CreationTimestamp
    @Column(nullable = false, name = "sent_at")
    private ZonedDateTime sentAt;

}
