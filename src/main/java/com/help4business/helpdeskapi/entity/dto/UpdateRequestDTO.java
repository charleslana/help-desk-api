package com.help4business.helpdeskapi.entity.dto;

import com.help4business.helpdeskapi.entity.Request;
import com.help4business.helpdeskapi.enumeration.RequestPriorityEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateRequestDTO {
    @NotNull
    @NotBlank
    @Length(min = 10, max = 1000)
    private String description;

    @NotNull
    private Long id;

    @NotNull
    private RequestPriorityEnum priority;

    public Request convertToEntity() {
        Request request = new Request();
        request.setId(id);
        request.setDescription(description);
        request.setPriority(priority);
        return request;
    }
}
