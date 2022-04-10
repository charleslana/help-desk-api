package com.help4business.helpdeskapi.entity.dto;

import com.help4business.helpdeskapi.entity.User;
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

    public User convertToEntity() {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setCountry(country);
        user.setPhoneNumber(phoneNumber);
        user.setAccountType(accountType);
        user.setStatus(status);
        return user;
    }
}
