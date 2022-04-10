package com.help4business.helpdeskapi.entity.dto;

import com.help4business.helpdeskapi.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UpdateUserDTO {
    @NotNull
    @NotBlank
    @Length(max = 50)
    private String country;

    @NotNull
    private Long id;

    @NotNull
    @NotBlank
    @Length(max = 50)
    private String name;

    @Pattern(regexp = "^\\d{11}$", message = "phone number should be valid")
    private String phoneNumber;

    public User convertToEntity() {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCountry(country);
        user.setPhoneNumber(phoneNumber);
        return user;
    }
}
