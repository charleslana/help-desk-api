package com.help4business.helpdeskapi.entity.dto;

import com.help4business.helpdeskapi.entity.Request;
import com.help4business.helpdeskapi.entity.User;
import com.help4business.helpdeskapi.enumeration.RequestPriorityEnum;
import com.help4business.helpdeskapi.enumeration.RequestStatusEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Setter
public class CreateRequestDTO {
    @NotNull
    @NotBlank
    @Length(min = 10, max = 1000)
    private String description;

    @NotNull
    private RequestPriorityEnum priority;

    @Null
    private RequestStatusEnum status;

    @NotNull
    private Long userId;

    public Request convertToEntity() {
        Request request = new Request();
        request.setDescription(description);
        request.setStatus(status);
        request.setPriority(priority);
        User user = new User();
        user.setId(userId);
        request.setUser(user);
        return request;
    }
}
