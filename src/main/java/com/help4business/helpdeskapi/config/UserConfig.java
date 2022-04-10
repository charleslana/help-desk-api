package com.help4business.helpdeskapi.config;

import com.help4business.helpdeskapi.entity.Request;
import com.help4business.helpdeskapi.entity.User;
import com.help4business.helpdeskapi.enumeration.AccountType;
import com.help4business.helpdeskapi.enumeration.RequestPriorityEnum;
import com.help4business.helpdeskapi.enumeration.RequestStatusEnum;
import com.help4business.helpdeskapi.enumeration.UserStatusEnum;
import com.help4business.helpdeskapi.repository.RequestRepository;
import com.help4business.helpdeskapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

//@Configuration
@RequiredArgsConstructor
public class UserConfig {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    @Bean
    CommandLineRunner insertUser() {
        return args -> {
            requestRepository.deleteAll();
            userRepository.deleteAll();

            User user = new User();
            user.setEmail("test@test.com");
            user.setPassword("123456");
            user.setName("Test");
            user.setCountry("Brasil");
            user.setAccountType(AccountType.USER);
            user.setStatus(UserStatusEnum.ACTIVE);

            User user2 = new User();
            user2.setEmail("test2@test.com");
            user2.setPassword("123456789");
            user2.setName("Test2");
            user2.setCountry("Estados Unidos");
            user2.setAccountType(AccountType.ADMIN);
            user2.setStatus(UserStatusEnum.INACTIVE);

            userRepository.saveAll(List.of(user, user2));

            Long insertUser = user.getId();
            Long insertUser2 = user2.getId();

            Request request = new Request();
            request.setDescription("Description 1");
            request.setPriority(RequestPriorityEnum.LOW);
            request.setStatus(RequestStatusEnum.OPENED);
            User userRequest = new User();
            userRequest.setId(insertUser);
            request.setUser(userRequest);

            Request request2 = new Request();
            request2.setDescription("Description 2");
            request2.setPriority(RequestPriorityEnum.CRITICAL);
            request2.setStatus(RequestStatusEnum.PROCESSING);
            User userRequest2 = new User();
            userRequest2.setId(insertUser2);
            request2.setUser(userRequest2);

            requestRepository.saveAll(List.of(request, request2));
        };
    }
}
