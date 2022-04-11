package com.help4business.helpdeskapi.entity.dto;

import com.help4business.helpdeskapi.entity.AppUser;
import com.help4business.helpdeskapi.enumeration.AccountType;
import com.help4business.helpdeskapi.enumeration.UserStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserDTO {

    private AccountType accountType;
    private String email;
    private Long id;
    private String name;
    private String phoneNumber;
    private UserStatusEnum status;

    public static GetUserDTO convertToDto(AppUser appUser) {
        GetUserDTO getUserDTO = new GetUserDTO();
        getUserDTO.setId(appUser.getId());
        getUserDTO.setEmail(appUser.getEmail());
        getUserDTO.setName(appUser.getName());
        getUserDTO.setPhoneNumber(appUser.getPhoneNumber());
        getUserDTO.setAccountType(appUser.getAccountType());
        getUserDTO.setStatus(appUser.getStatus());
        return getUserDTO;
    }
}
