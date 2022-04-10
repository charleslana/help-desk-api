package com.help4business.helpdeskapi.entity.dto;

import com.help4business.helpdeskapi.entity.User;
import com.help4business.helpdeskapi.enumeration.AccountType;
import com.help4business.helpdeskapi.enumeration.UserStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ListUserDTO {

    private AccountType accountType;
    private LocalDateTime createdAt;
    private String email;
    private Long id;
    private String name;
    private String phoneNumber;
    private UserStatusEnum status;

    public static ListUserDTO convertToDto(User user) {
        ListUserDTO listUserDTO = new ListUserDTO();
        listUserDTO.setId(user.getId());
        listUserDTO.setName(user.getName());
        listUserDTO.setEmail(user.getEmail());
        listUserDTO.setPhoneNumber(user.getPhoneNumber());
        listUserDTO.setCreatedAt(user.getCreatedAt());
        listUserDTO.setAccountType(user.getAccountType());
        listUserDTO.setStatus(user.getStatus());
        return listUserDTO;
    }
}
