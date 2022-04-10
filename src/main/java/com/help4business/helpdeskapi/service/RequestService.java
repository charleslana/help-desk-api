package com.help4business.helpdeskapi.service;

import com.help4business.helpdeskapi.entity.Request;
import com.help4business.helpdeskapi.entity.dto.CreateRequestDTO;
import com.help4business.helpdeskapi.entity.dto.RequestCountDTO;
import com.help4business.helpdeskapi.entity.dto.RequestCountTotalizerDTO;
import com.help4business.helpdeskapi.enumeration.RequestStatusEnum;
import com.help4business.helpdeskapi.exceptions.ObjectNotFoundException;
import com.help4business.helpdeskapi.repository.RequestRepository;
import com.help4business.helpdeskapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;

    public void addNewRequest(CreateRequestDTO request) {
        boolean exists = userRepository.existsById(request.getUserId());
        if (!exists) {
            throw new ObjectNotFoundException(String.format("user with id %d does not exists", request.getUserId()));
        }
        request.setStatus(RequestStatusEnum.OPENED);
        requestRepository.save(request.convertToEntity());
    }

    public void deleteRequest(Long requestId) {
        Optional<Request> requestOptional = requestRepository.findRequestByIdAndStatus(requestId, RequestStatusEnum.OPENED);
        if (requestOptional.isEmpty()) {
            throw new ObjectNotFoundException(String.format("request with id %d does not exists or the request is not open", requestId));
        }
        requestRepository.deleteById(requestId);
    }

    public Request getRequest(Long requestId) {
        return requestRepository.findById(requestId).orElseThrow(() -> new ObjectNotFoundException(String.format("request with id %d does not exists", requestId)));
    }

    public List<Request> getRequests() {
        return requestRepository.findAll();
    }

    public RequestCountTotalizerDTO getTotalizer() {
        // TODO: 09/04/2022 Pegar do usuário logado
        List<RequestCountDTO> requestCountDTOS = requestRepository.countRequestByUserId(6L);
        RequestCountTotalizerDTO requestCountTotalizerDTO = new RequestCountTotalizerDTO();
        bindValues(requestCountDTOS, requestCountTotalizerDTO);
        calculateTotal(requestCountTotalizerDTO);
        return requestCountTotalizerDTO;
    }

    private void calculateTotal(RequestCountTotalizerDTO result) {
        result.setOpened(Optional.ofNullable(result.getOpened()).orElse(0L));
        result.setProcessing(Optional.ofNullable(result.getProcessing()).orElse(0L));
        result.setFinished(Optional.ofNullable(result.getFinished()).orElse(0L));
        result.setTotal(result.getCount());
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

    @Transactional
    public void updateRequest(Request request) {
        Request existRequest = requestRepository.findRequestByIdAndStatus(request.getId(), RequestStatusEnum.OPENED).orElseThrow(() -> new ObjectNotFoundException(String.format("request with id %d does not exists or the request is not open", request.getId())));
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
