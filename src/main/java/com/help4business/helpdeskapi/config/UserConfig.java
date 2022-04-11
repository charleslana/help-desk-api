package com.help4business.helpdeskapi.config;

import com.help4business.helpdeskapi.entity.AppUser;
import com.help4business.helpdeskapi.entity.Request;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class UserConfig {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Bean
    CommandLineRunner insertUser() {
        return args -> {
            requestRepository.deleteAll();
            userRepository.deleteAll();

            AppUser appUser = new AppUser();
            appUser.setEmail("test@test.com");
            appUser.setPassword(encoder.encode("123456"));
            appUser.setName("Test");
            appUser.setCountry("Brasil");
            appUser.setAccountType(AccountType.USER);
            appUser.setStatus(UserStatusEnum.ACTIVE);

            AppUser appUser2 = new AppUser();
            appUser2.setEmail("test2@test.com");
            appUser2.setPassword(encoder.encode("123456"));
            appUser2.setName("Test2");
            appUser2.setCountry("Estados Unidos");
            appUser2.setAccountType(AccountType.ADMIN);
            appUser2.setStatus(UserStatusEnum.INACTIVE);

            userRepository.saveAll(List.of(appUser, appUser2));

            Long insertUser = appUser.getId();
            Long insertUser2 = appUser2.getId();

            Request request = new Request();
            request.setDescription("Description 1");
            request.setPriority(RequestPriorityEnum.LOW);
            request.setStatus(RequestStatusEnum.OPENED);
            AppUser appUserRequest = new AppUser();
            appUserRequest.setId(insertUser);
            request.setAppUser(appUserRequest);

            Request request2 = new Request();
            request2.setDescription("Description 2");
            request2.setPriority(RequestPriorityEnum.CRITICAL);
            request2.setStatus(RequestStatusEnum.PROCESSING);
            AppUser appUserRequest2 = new AppUser();
            appUserRequest2.setId(insertUser2);
            request2.setAppUser(appUserRequest2);

            requestRepository.saveAll(List.of(request, request2));
        };
    }
}
