package com.cs.vox.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Size(max = 100, message = "Name too long")
    @Pattern(regexp = "^[a-zA-Z0-9 _,.-]*$", message = "Invalid characters")
    private String name;

    @Size(max = 500, message = "Description is too long")
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private ZonedDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "created_by", updatable = false)
    private User createdBy;
}
