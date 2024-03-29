package com.help4business.helpdeskapi.entity.dto;

import com.help4business.helpdeskapi.entity.Request;
import com.help4business.helpdeskapi.enumeration.RequestPriorityEnum;
import com.help4business.helpdeskapi.enumeration.RequestStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ListRequestDTO {

    private LocalDateTime createdAt;
    private Long id;
    private RequestPriorityEnum priority;
    private RequestStatusEnum status;

    public static ListRequestDTO convertToDto(Request request) {
        ListRequestDTO listRequestDTO = new ListRequestDTO();
        listRequestDTO.setId(request.getId());
        listRequestDTO.setPriority(request.getPriority());
        listRequestDTO.setStatus(request.getStatus());
        listRequestDTO.setCreatedAt(request.getCreatedAt());
        return listRequestDTO;
    }
}
