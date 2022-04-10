package com.help4business.helpdeskapi.controller;

import com.help4business.helpdeskapi.entity.Request;
import com.help4business.helpdeskapi.entity.dto.*;
import com.help4business.helpdeskapi.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/request")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @DeleteMapping(path = "{requestId}")
    public void deleteRequest(@PathVariable("requestId") Long requestId) {
        requestService.deleteRequest(requestId);
    }

    @GetMapping(path = "{requestId}")
    public Request getRequest(@PathVariable("requestId") Long requestId) {
        return requestService.getRequest(requestId);
    }

    @GetMapping
    public List<ListRequestDTO> getRequests() {
        List<Request> requests = requestService.getRequests();
        return requests.stream().map(ListRequestDTO::convertToDto).collect(Collectors.toList());
    }

    @PostMapping
    public void registerNewRequest(@RequestBody @Valid CreateRequestDTO request) {
        requestService.addNewRequest(request);
    }

    @PutMapping
    public void updateRequest(@RequestBody @Valid UpdateRequestDTO request) {
        requestService.updateRequest(request.convertToEntity());
    }

    @PutMapping("/change-status")
    public void updateStatusRequest(@RequestBody @Valid UpdateRequestStatusDTO request) {
        requestService.updateRequestStatus(request.convertToEntity());
    }

    @GetMapping("/totalizer")
    public RequestCountTotalizerDTO getTotalizer() {
        return requestService.getTotalizer();
    }
}
