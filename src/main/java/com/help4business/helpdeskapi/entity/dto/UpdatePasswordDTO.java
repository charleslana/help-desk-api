package com.help4business.helpdeskapi.entity.dto;

import com.help4business.helpdeskapi.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdatePasswordDTO {
    @NotNull
    private Long id;

    @NotNull
    @NotBlank
    @Length(min = 6)
    private String password;

    public User convertToEntity() {
        User user = new User();
        user.setId(id);
        user.setPassword(password);
        return user;
    }
}
