package com.help4business.helpdeskapi.entity.dto;

import com.help4business.helpdeskapi.entity.Request;
import com.help4business.helpdeskapi.enumeration.RequestStatusEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateRequestStatusDTO {
    @NotNull
    @Min(1)
    private Long id;

    @Length(max = 1000)
    private String justify;

    @NotNull
    private RequestStatusEnum status;

    public Request convertToEntity() {
        Request request = new Request();
        request.setId(id);
        request.setJustify(justify);
        request.setStatus(status);
        return request;
    }
}
