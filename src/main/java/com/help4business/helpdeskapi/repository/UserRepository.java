package com.help4business.helpdeskapi.repository;

import com.help4business.helpdeskapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT s FROM User s WHERE s.email = :email")
    Optional<User> findUserByEmail(String email);
}
