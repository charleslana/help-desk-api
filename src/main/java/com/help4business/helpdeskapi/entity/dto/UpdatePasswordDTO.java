package com.help4business.helpdeskapi.entity.dto;

import com.help4business.helpdeskapi.entity.AppUser;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdatePasswordDTO {
    @NotNull
    @NotBlank
    @Length(min = 6)
    private String password;

    public AppUser convertToEntity() {
        AppUser appUser = new AppUser();
        appUser.setPassword(password);
        return appUser;
    }
}
