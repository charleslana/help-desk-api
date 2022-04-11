package com.help4business.helpdeskapi.entity.dto;

import com.help4business.helpdeskapi.entity.AppUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserDetailDTO {

    private String country;
    private String email;
    private Long id;
    private String name;
    private String phoneNumber;

    public static GetUserDetailDTO convertToDto(AppUser appUser) {
        GetUserDetailDTO getUserDetailDTO = new GetUserDetailDTO();
        getUserDetailDTO.setId(appUser.getId());
        getUserDetailDTO.setEmail(appUser.getEmail());
        getUserDetailDTO.setName(appUser.getName());
        getUserDetailDTO.setCountry(appUser.getCountry());
        getUserDetailDTO.setPhoneNumber(appUser.getPhoneNumber());
        return getUserDetailDTO;
    }
}
