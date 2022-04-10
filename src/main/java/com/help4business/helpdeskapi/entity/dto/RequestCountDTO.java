package com.help4business.helpdeskapi.entity.dto;

import com.help4business.helpdeskapi.enumeration.RequestStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCountDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long quantity;
    private RequestStatusEnum status;
}
