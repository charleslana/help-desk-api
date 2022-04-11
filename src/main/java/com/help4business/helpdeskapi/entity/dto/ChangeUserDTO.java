package com.help4business.helpdeskapi.entity.dto;

import com.help4business.helpdeskapi.entity.AppUser;
import com.help4business.helpdeskapi.enumeration.AccountType;
import com.help4business.helpdeskapi.enumeration.UserStatusEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ChangeUserDTO {
    @NotNull
    private AccountType accountType;

    @NotNull
    @Min(1)
    private Long id;

    @NotNull
    private UserStatusEnum status;

    public AppUser convertToEntity() {
        AppUser appUser = new AppUser();
        appUser.setId(id);
        appUser.setAccountType(accountType);
        appUser.setStatus(status);
        return appUser;
    }
}
