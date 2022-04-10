package com.help4business.helpdeskapi.entity.dto;

import com.help4business.helpdeskapi.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserDTO {

    private String country;
    private String email;
    private Long id;
    private String name;
    private String phoneNumber;

    public static GetUserDTO convertToDto(User user) {
        GetUserDTO getUserDTO = new GetUserDTO();
        getUserDTO.setId(user.getId());
        getUserDTO.setEmail(user.getEmail());
        getUserDTO.setName(user.getName());
        getUserDTO.setCountry(user.getCountry());
        getUserDTO.setPhoneNumber(user.getPhoneNumber());
        return getUserDTO;
    }
}
