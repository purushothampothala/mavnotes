package com.maveric.csp.repository;

import com.maveric.csp.entity.Customer;
import com.maveric.csp.entity.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SessionRepositoryTest {
    @Autowired
    public SessionRepository repository;

    Session session;
    Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        session = new Session("SESSION_1", "sessionName", "purushotham", new Date(), new Date(), 'A', "No remarks", null);
        repository.save(session);
    }

    @AfterEach
    void tearDown() {
        session = null;
        repository.deleteAll();

    }
    @Test
    void testFindByStatus_Successful() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Session> sessions = repository.findByStatus('A', "purushotham", pageable);
        assertEquals(4, sessions.getTotalElements());
        assertEquals(session.getCreatedBy(), sessions.getContent().get(0).getCreatedBy());
    }

    @Test
    void testFindByStatus_NotFound() {
        Page<Session> sessions = repository.findByStatus('B', "purushotham", PageRequest.of(0, 10));
        assertTrue(sessions.isEmpty());
    }
    @Test
    void testFindBySessionId() {
        Optional<Session> sessions = repository.findBySessionId("SESSION_1");
        assertTrue(sessions.isPresent());
        assertEquals(session.getSessionId(), sessions.get().getSessionId());
    }

    @Test
    void testFindBySessionId_NotFound() {
        Optional<Session> sessions = repository.findBySessionId("SESSION_55");
        assertTrue(sessions.isEmpty());
    }


}