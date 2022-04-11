package com.help4business.helpdeskapi.service;

import com.help4business.helpdeskapi.entity.AppUser;
import com.help4business.helpdeskapi.entity.Request;
import com.help4business.helpdeskapi.entity.dto.CreateRequestDTO;
import com.help4business.helpdeskapi.entity.dto.RequestCountDTO;
import com.help4business.helpdeskapi.entity.dto.RequestCountTotalizerDTO;
import com.help4business.helpdeskapi.enumeration.AccountType;
import com.help4business.helpdeskapi.enumeration.RequestStatusEnum;
import com.help4business.helpdeskapi.exceptions.ObjectNotFoundException;
import com.help4business.helpdeskapi.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;
    private final UserService userService;

    public void addNewRequest(CreateRequestDTO request) {
        AppUser existAppUser = userService.getAuthUser();
        request.setUserId(existAppUser.getId());
        request.setStatus(RequestStatusEnum.OPENED);
        requestRepository.save(request.convertToEntity());
    }

    private void bindValues(List<RequestCountDTO> requestCountDTOS, RequestCountTotalizerDTO requestCountTotalizerDTO) {
        requestCountDTOS.forEach(requestCountDTO -> {
            switch (requestCountDTO.getStatus()) {
                case OPENED:
                    requestCountTotalizerDTO.setOpened(requestCountDTO.getQuantity());
                    break;
                case PROCESSING:
                    requestCountTotalizerDTO.setProcessing(requestCountDTO.getQuantity());
                    break;
                case FINISHED:
                    requestCountTotalizerDTO.setFinished(requestCountDTO.getQuantity());
                    break;
            }
        });
    }

    private void calculateTotal(RequestCountTotalizerDTO result) {
        result.setOpened(Optional.ofNullable(result.getOpened()).orElse(0L));
        result.setProcessing(Optional.ofNullable(result.getProcessing()).orElse(0L));
        result.setFinished(Optional.ofNullable(result.getFinished()).orElse(0L));
        result.setTotal(result.getCount());
    }

    public void deleteRequest(Long requestId) {
        Optional<Request> requestOptional = requestRepository.findRequestByIdAndStatusAndAppUserId(requestId, RequestStatusEnum.OPENED, userService.getAuthUser().getId());
        if (requestOptional.isEmpty()) {
            throw new ObjectNotFoundException(String.format("request with id %d does not exists or the request is not open", requestId));
        }
        requestRepository.deleteById(requestId);
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public Request getRequest(Long requestId) {
        return requestRepository.findById(requestId).orElseThrow(() -> new ObjectNotFoundException(String.format("request with id %d does not exists", requestId)));
    }

    public Request getRequestDetail(Long requestId) {
        return requestRepository.findByIdAndAppUserId(requestId, userService.getAuthUser().getId()).orElseThrow(() -> new ObjectNotFoundException(String.format("request with id %d does not exists", requestId)));
    }

    public List<Request> getRequests() {
        return requestRepository.findAllByAppUserId(userService.getAuthUser().getId());
    }

    public RequestCountTotalizerDTO getTotalizer() {
        AppUser authUser = userService.getAuthUser();
        List<RequestCountDTO> requestCountDTOS;
        if (AccountType.ADMIN.equals(authUser.getAccountType())) {
            requestCountDTOS = requestRepository.countRequestTotal();
        } else {
            requestCountDTOS = requestRepository.countRequestByUserId(authUser.getId());
        }
        RequestCountTotalizerDTO requestCountTotalizerDTO = new RequestCountTotalizerDTO();
        bindValues(requestCountDTOS, requestCountTotalizerDTO);
        calculateTotal(requestCountTotalizerDTO);
        return requestCountTotalizerDTO;
    }

    @Transactional
    public void updateRequest(Request request) {
        Request existRequest = requestRepository.findRequestByIdAndStatusAndAppUserId(request.getId(), RequestStatusEnum.OPENED, userService.getAuthUser().getId()).orElseThrow(() -> new ObjectNotFoundException(String.format("request with id %d does not exists or the request is not open", request.getId())));
        existRequest.setDescription(request.getDescription());
        existRequest.setPriority(request.getPriority());
    }

    @Transactional
    public void updateRequestStatus(Request request) {
        Request existRequest = requestRepository.findById(request.getId()).orElseThrow(() -> new ObjectNotFoundException(String.format("request with id %d does not exists", request.getId())));
        existRequest.setJustify(request.getJustify());
        existRequest.setStatus(request.getStatus());
    }
}
