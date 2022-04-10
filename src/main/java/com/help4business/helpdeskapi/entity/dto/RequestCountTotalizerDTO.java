package com.help4business.helpdeskapi.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RequestCountTotalizerDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long finished;
    private Long opened;
    private Long processing;
    private Long total;

    @JsonIgnore
    public Long getCount() {
        return opened + processing + finished;
    }
}
