package com.help4business.helpdeskapi.controller;

import com.help4business.helpdeskapi.entity.AppUser;
import com.help4business.helpdeskapi.entity.dto.*;
import com.help4business.helpdeskapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/change-user")
    public void changeUser(@RequestBody @Valid ChangeUserDTO user) {
        userService.changeUser(user.convertToEntity());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "{userId}")
    public GetUserDTO getUser(@PathVariable("userId") Long userId) {
        AppUser appUser = userService.getUser(userId);
        return GetUserDTO.convertToDto(appUser);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @GetMapping("/detail")
    public GetUserDetailDTO getUserDetail() {
        AppUser appUser = userService.getUserDetail();
        return GetUserDetailDTO.convertToDto(appUser);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<ListUserDTO> getUsers() {
        List<AppUser> appUsers = userService.getUsers();
        return appUsers.stream().map(ListUserDTO::convertToDto).collect(Collectors.toList());
    }

    @PostMapping
    public void registerNewUser(@RequestBody @Valid CreateUserDTO user) {
        userService.addNewUser(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @PutMapping("/change-password")
    public void updatePassword(@RequestBody @Valid UpdatePasswordDTO user) {
        userService.updatePassword(user.convertToEntity());
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping
    public void updateUser(@RequestBody @Valid UpdateUserDTO user) {
        userService.updateUser(user.convertToEntity());
    }
}
