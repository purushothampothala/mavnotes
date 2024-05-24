package com.maveric.csp.repository;

import com.maveric.csp.entity.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session,String> {

    Optional<Session> findBySessionId(String sessionId);

    //@Query("SELECT s FROM Session s ORDER BY s.created_on DESC")
    Page<Session> findAll(Pageable pageable);
    @Query(value = "SELECT * FROM session WHERE status = ?1 AND created_by = ?2 ORDER BY created_on DESC",
            nativeQuery = true)
    Page<Session> findByStatus(char status,String createdBy,Pageable pageable);

    Optional<Session> deleteBySessionId(String sessionId);
}
