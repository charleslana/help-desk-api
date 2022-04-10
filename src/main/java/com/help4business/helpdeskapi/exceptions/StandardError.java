package com.help4business.helpdeskapi.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class StandardError {

    private Integer status;
    private Long timestamp;
    private String message;
}
