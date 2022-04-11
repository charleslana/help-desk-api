package com.help4business.helpdeskapi.controller;

import com.help4business.helpdeskapi.entity.Request;
import com.help4business.helpdeskapi.entity.dto.*;
import com.help4business.helpdeskapi.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/request")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @DeleteMapping(path = "{requestId}")
    public void deleteRequest(@PathVariable("requestId") Long requestId) {
        requestService.deleteRequest(requestId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public List<ListRequestDTO> getAllRequests() {
        List<Request> requests = requestService.getAllRequests();
        return requests.stream().map(ListRequestDTO::convertToDto).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "{requestId}")
    public GetRequestDTO getRequest(@PathVariable("requestId") Long requestId) {
        Request request = requestService.getRequest(requestId);
        return GetRequestDTO.convertToDto(request);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @GetMapping(path = "{requestId}/detail")
    public GetRequestDetailDTO getRequestDetail(@PathVariable("requestId") Long requestId) {
        Request request = requestService.getRequestDetail(requestId);
        return GetRequestDetailDTO.convertToDto(request);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @GetMapping
    public List<ListRequestDTO> getRequests() {
        List<Request> requests = requestService.getRequests();
        return requests.stream().map(ListRequestDTO::convertToDto).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @GetMapping("/totalizer")
    public RequestCountTotalizerDTO getTotalizer() {
        return requestService.getTotalizer();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @PostMapping
    public void registerNewRequest(@RequestBody @Valid CreateRequestDTO request) {
        requestService.addNewRequest(request);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @PutMapping
    public void updateRequest(@RequestBody @Valid UpdateRequestDTO request) {
        requestService.updateRequest(request.convertToEntity());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/change-status")
    public void updateStatusRequest(@RequestBody @Valid UpdateRequestStatusDTO request) {
        requestService.updateRequestStatus(request.convertToEntity());
    }
}
