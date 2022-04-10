package com.help4business.helpdeskapi.entity.dto;

import com.help4business.helpdeskapi.entity.User;
import com.help4business.helpdeskapi.enumeration.AccountType;
import com.help4business.helpdeskapi.enumeration.UserStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserDTO {

    private AccountType accountType;
    private String country;
    private String email;
    private Long id;
    private String name;
    private String phoneNumber;
    private UserStatusEnum status;

    public static GetUserDTO convertToDto(User user) {
        GetUserDTO getUserDTO = new GetUserDTO();
        getUserDTO.setId(user.getId());
        getUserDTO.setEmail(user.getEmail());
        getUserDTO.setName(user.getName());
        getUserDTO.setCountry(user.getCountry());
        getUserDTO.setPhoneNumber(user.getPhoneNumber());
        getUserDTO.setAccountType(user.getAccountType());
        getUserDTO.setStatus(user.getStatus());
        return getUserDTO;
    }
}
