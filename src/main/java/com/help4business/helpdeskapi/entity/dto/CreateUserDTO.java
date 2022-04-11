package com.help4business.helpdeskapi.entity.dto;

import com.help4business.helpdeskapi.entity.AppUser;
import com.help4business.helpdeskapi.enumeration.AccountType;
import com.help4business.helpdeskapi.enumeration.UserStatusEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Getter
@Setter
public class CreateUserDTO {
    @NotNull
    private AccountType accountType;

    @NotNull
    @NotBlank
    @Length(max = 50)
    private String country;

    @NotNull
    @NotBlank
    @Email(message = "email should be valid")
    private String email;

    @NotNull
    @NotBlank
    @Length(max = 50)
    private String name;

    @NotNull
    @NotBlank
    @Length(min = 6)
    private String password;

    @Pattern(regexp = "^\\d{11}$", message = "phone number should be valid")
    private String phoneNumber;

    @Null
    private UserStatusEnum status;

    public AppUser convertToEntity() {
        AppUser appUser = new AppUser();
        appUser.setEmail(email);
        appUser.setPassword(password);
        appUser.setName(name);
        appUser.setCountry(country);
        appUser.setPhoneNumber(phoneNumber);
        appUser.setAccountType(accountType);
        appUser.setStatus(status);
        return appUser;
    }
}
