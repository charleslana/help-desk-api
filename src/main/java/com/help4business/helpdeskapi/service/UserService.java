package com.help4business.helpdeskapi.service;

import com.help4business.helpdeskapi.config.security.SecurityUtils;
import com.help4business.helpdeskapi.entity.AppUser;
import com.help4business.helpdeskapi.entity.dto.CreateUserDTO;
import com.help4business.helpdeskapi.enumeration.AccountType;
import com.help4business.helpdeskapi.enumeration.UserStatusEnum;
import com.help4business.helpdeskapi.exceptions.ObjectExistsException;
import com.help4business.helpdeskapi.exceptions.ObjectNotFoundException;
import com.help4business.helpdeskapi.repository.RequestRepository;
import com.help4business.helpdeskapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder encoder;
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    @Transactional
    public void addNewUser(CreateUserDTO user) {
        log.info("Saving new user {} to the database", user.getName());
        Optional<AppUser> userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new ObjectExistsException("the email is already in use");
        }
        if (user.getAccountType() == AccountType.USER) {
            user.setStatus(UserStatusEnum.ACTIVE);
        } else {
            user.setStatus(UserStatusEnum.INACTIVE);
        }
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user.convertToEntity());
    }

    @Transactional
    public void changeUser(AppUser appUser) {
        AppUser existAppUser = userRepository.findById(appUser.getId()).orElseThrow(() -> new ObjectNotFoundException(String.format("user with id %d does not exists", appUser.getId())));
        existAppUser.setAccountType(appUser.getAccountType());
        existAppUser.setStatus(appUser.getStatus());
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new ObjectNotFoundException(String.format("user with id %d does not exists", userId));
        }
        boolean existsRequestUser = requestRepository.existsByAppUserId(userId);
        if (existsRequestUser) {
            throw new ObjectExistsException(String.format("there are requests to the user id %d", userId));
        }
        userRepository.deleteById(userId);
    }

    public AppUser getAuthUser() {
        String authEmail = SecurityUtils.getAuthEmail();
        return getUserByEmail(authEmail);
    }

    public List<GrantedAuthority> getRoles(String email) {
        AppUser appUser = getUserByEmail(email);
        return Collections.singletonList(new SimpleGrantedAuthority(AccountType.ADMIN.equals(appUser.getAccountType()) ? "ROLE_ADMIN" : "ROLE_USER"));
    }

    public AppUser getUser(Long userId) {
        log.info("Fetching user {}", userId);
        return userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException(String.format("user with id %d does not exists", userId)));
    }

    public AppUser getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new ObjectNotFoundException(String.format("email %s does not exists", email)));
    }

    public AppUser getUserDetail() {
        return userRepository.findById(getAuthUser().getId()).orElseThrow(() -> new ObjectNotFoundException(String.format("user with id %d does not exists", getAuthUser().getId())));
    }

    public List<AppUser> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findAppUserByEmailAndStatusNot(email, UserStatusEnum.INACTIVE).orElseThrow(() -> new ObjectNotFoundException(String.format("email %s does not exists or user not active", email)));
        List<GrantedAuthority> roles = Collections.singletonList(new SimpleGrantedAuthority(AccountType.ADMIN.equals(appUser.getAccountType()) ? "ROLE_ADMIN" : "ROLE_USER"));
        return new User(appUser.getEmail(), appUser.getPassword(), roles);
    }

    @Transactional
    public void updatePassword(AppUser appUser) {
        AppUser existAppUser = getAuthUser();
        existAppUser.setPassword(encoder.encode(appUser.getPassword()));
    }

    @Transactional
    public void updateUser(AppUser appUser) {
        AppUser existAppUser = getAuthUser();
        existAppUser.setName(appUser.getName());
        existAppUser.setCountry(appUser.getCountry());
        existAppUser.setPhoneNumber(appUser.getPhoneNumber());
    }
}
