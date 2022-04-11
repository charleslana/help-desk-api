package com.help4business.helpdeskapi.repository;

import com.help4business.helpdeskapi.entity.AppUser;
import com.help4business.helpdeskapi.enumeration.UserStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    @Query("SELECT s FROM AppUser s WHERE s.email = :email")
    Optional<AppUser> findUserByEmail(String email);

    Optional<AppUser> findAppUserByEmailAndStatusNot(String email, UserStatusEnum status);
}
