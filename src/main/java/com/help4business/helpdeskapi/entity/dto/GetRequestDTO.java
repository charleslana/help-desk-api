package com.help4business.helpdeskapi.entity.dto;

import com.help4business.helpdeskapi.entity.Request;
import com.help4business.helpdeskapi.enumeration.RequestPriorityEnum;
import com.help4business.helpdeskapi.enumeration.RequestStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetRequestDTO {

    private LocalDateTime createdAt;
    private String description;
    private Long id;
    private String justify;
    private RequestPriorityEnum priority;
    private RequestStatusEnum status;
    private LocalDateTime updatedAt;

    public static GetRequestDTO convertToDto(Request request) {
        GetRequestDTO listRequestDTO = new GetRequestDTO();
        listRequestDTO.setId(request.getId());
        listRequestDTO.setPriority(request.getPriority());
        listRequestDTO.setStatus(request.getStatus());
        listRequestDTO.setCreatedAt(request.getCreatedAt());
        listRequestDTO.setUpdatedAt(request.getUpdatedAt());
        listRequestDTO.setJustify(request.getJustify());
        listRequestDTO.setDescription(request.getDescription());
        return listRequestDTO;
    }
}
