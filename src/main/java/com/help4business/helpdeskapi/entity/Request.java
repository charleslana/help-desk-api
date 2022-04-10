package com.help4business.helpdeskapi.entity;

import com.help4business.helpdeskapi.enumeration.RequestPriorityEnum;
import com.help4business.helpdeskapi.enumeration.RequestStatusEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "request")
@RequiredArgsConstructor
public class Request {
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    @Column(name = "id")
    @Id
    @SequenceGenerator(
            name = "request_sequence",
            sequenceName = "request_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "request_sequence"
    )
    private Long id;

    @Column(name = "justify", length = 1000)
    private String justify;

    @Column(name = "priority", nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestPriorityEnum priority;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestStatusEnum status;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
