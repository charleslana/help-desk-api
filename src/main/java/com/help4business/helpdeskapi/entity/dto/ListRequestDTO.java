package com.help4business.helpdeskapi.entity.dto;

import com.help4business.helpdeskapi.entity.Request;
import com.help4business.helpdeskapi.enumeration.RequestStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListRequestDTO {

    private String description;
    private Long id;
    private String justify;
    private RequestStatusEnum status;

    public static ListRequestDTO convertToDto(Request request) {
        ListRequestDTO listRequestDTO = new ListRequestDTO();
        listRequestDTO.setId(request.getId());
        listRequestDTO.setDescription(request.getDescription());
        listRequestDTO.setStatus(request.getStatus());
        listRequestDTO.setJustify(request.getJustify());
        return listRequestDTO;
    }
}
