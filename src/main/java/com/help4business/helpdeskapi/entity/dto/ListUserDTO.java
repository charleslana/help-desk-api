package com.help4business.helpdeskapi.entity.dto;

import com.help4business.helpdeskapi.entity.AppUser;
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
    private UserStatusEnum status;

    public static ListUserDTO convertToDto(AppUser appUser) {
        ListUserDTO listUserDTO = new ListUserDTO();
        listUserDTO.setId(appUser.getId());
        listUserDTO.setName(appUser.getName());
        listUserDTO.setEmail(appUser.getEmail());
        listUserDTO.setCreatedAt(appUser.getCreatedAt());
        listUserDTO.setAccountType(appUser.getAccountType());
        listUserDTO.setStatus(appUser.getStatus());
        return listUserDTO;
    }
}
