package com.help4business.helpdeskapi.service;

import com.help4business.helpdeskapi.entity.User;
import com.help4business.helpdeskapi.entity.dto.CreateUserDTO;
import com.help4business.helpdeskapi.enumeration.AccountType;
import com.help4business.helpdeskapi.enumeration.UserStatusEnum;
import com.help4business.helpdeskapi.exceptions.ObjectExistsException;
import com.help4business.helpdeskapi.exceptions.ObjectNotFoundException;
import com.help4business.helpdeskapi.repository.RequestRepository;
import com.help4business.helpdeskapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    public void addNewUser(CreateUserDTO user) {
        Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new ObjectExistsException("the email is already in use");
        }
        if (user.getAccountType() == AccountType.USER) {
            user.setStatus(UserStatusEnum.ACTIVE);
        } else {
            user.setStatus(UserStatusEnum.INACTIVE);
        }
        userRepository.save(user.convertToEntity());
    }

    @Transactional
    public void changeUser(User user) {
        User existUser = userRepository.findById(user.getId()).orElseThrow(() -> new ObjectNotFoundException(String.format("user with id %d does not exists", user.getId())));
        existUser.setAccountType(user.getAccountType());
        existUser.setStatus(user.getStatus());
    }

    public void deleteUser(Long userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new ObjectNotFoundException(String.format("user with id %d does not exists", userId));
        }
        boolean existsRequestUser = requestRepository.existsByUserId(userId);
        if (existsRequestUser) {
            throw new ObjectExistsException(String.format("there are requests to the user id %d", userId));
        }
        userRepository.deleteById(userId);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ObjectNotFoundException(String.format("user with id %d does not exists", userId)));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public void updatePassword(User user) {
        User existUser = userRepository.findById(user.getId()).orElseThrow(() -> new ObjectNotFoundException(String.format("user with id %d does not exists", user.getId())));
        existUser.setPassword(user.getPassword());
    }

    @Transactional
    public void updateUser(User user) {
        User existUser = userRepository.findById(user.getId()).orElseThrow(() -> new ObjectNotFoundException(String.format("user with id %d does not exists", user.getId())));
        existUser.setName(user.getName());
        existUser.setCountry(user.getCountry());
        existUser.setPhoneNumber(user.getPhoneNumber());
    }
}
