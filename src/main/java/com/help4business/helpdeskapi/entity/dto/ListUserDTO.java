package com.help4business.helpdeskapi.entity.dto;

import com.help4business.helpdeskapi.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListUserDTO {

    private String country;
    private String email;
    private Long id;
    private String name;
    private String phoneNumber;

    public static ListUserDTO convertToDto(User user) {
        ListUserDTO listUserDTO = new ListUserDTO();
        listUserDTO.setId(user.getId());
        listUserDTO.setEmail(user.getEmail());
        listUserDTO.setName(user.getName());
        listUserDTO.setCountry(user.getCountry());
        listUserDTO.setPhoneNumber(user.getPhoneNumber());
        return listUserDTO;
    }
}
