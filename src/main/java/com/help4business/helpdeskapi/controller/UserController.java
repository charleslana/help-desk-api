package com.help4business.helpdeskapi.controller;

import com.help4business.helpdeskapi.entity.User;
import com.help4business.helpdeskapi.entity.dto.CreateUserDTO;
import com.help4business.helpdeskapi.entity.dto.ListUserDTO;
import com.help4business.helpdeskapi.entity.dto.UpdatePasswordDTO;
import com.help4business.helpdeskapi.entity.dto.UpdateUserDTO;
import com.help4business.helpdeskapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping(path = "{userId}")
    public User getUser(@PathVariable("userId") Long userId) {
        return userService.getUser(userId);
    }

    @GetMapping
    public List<ListUserDTO> getUsers() {
        List<User> users = userService.getUsers();
        return users.stream().map(ListUserDTO::convertToDto).collect(Collectors.toList());
    }

    @PostMapping
    public void registerNewUser(@RequestBody @Valid CreateUserDTO user) {
        userService.addNewUser(user);
    }

    @PutMapping("/change-password")
    public void updatePassword(@RequestBody @Valid UpdatePasswordDTO user) {
        userService.updatePassword(user.convertToEntity());
    }

    @PutMapping
    public void updateUser(@RequestBody @Valid UpdateUserDTO user) {
        userService.updateUser(user.convertToEntity());
    }
}
