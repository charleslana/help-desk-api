package com.help4business.helpdeskapi.entity;

import com.help4business.helpdeskapi.enumeration.AccountType;
import com.help4business.helpdeskapi.enumeration.UserStatusEnum;
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
@Table(name = "user")
@RequiredArgsConstructor
public class User {
    @Column(name = "account_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "country", length = 50, nullable = false)
    private String country;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "email", length = 50, unique = true, nullable = false)
    private String email;

    @Column(name = "id")
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatusEnum status;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
