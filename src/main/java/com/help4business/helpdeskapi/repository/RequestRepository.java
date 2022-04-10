package com.help4business.helpdeskapi.repository;

import com.help4business.helpdeskapi.entity.Request;
import com.help4business.helpdeskapi.entity.dto.RequestCountDTO;
import com.help4business.helpdeskapi.enumeration.RequestStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query("SELECT NEW com.help4business.helpdeskapi.entity.dto.RequestCountDTO (COUNT(r.status), r.status) FROM Request r WHERE r.user.id = :userId GROUP BY r.status")
    List<RequestCountDTO> countRequestByUserId(Long userId);

    @Query("SELECT NEW com.help4business.helpdeskapi.entity.dto.RequestCountDTO (COUNT(r.status), r.status) FROM Request r GROUP BY r.status")
    List<RequestCountDTO> countRequestTotal();

    Boolean existsByUserId(Long id);

    @Query("SELECT r FROM Request r WHERE r.id = :id AND r.status = :status")
    Optional<Request> findRequestByIdAndStatus(Long id, RequestStatusEnum status);
}
