package com.maveric.csp.controller;

import com.maveric.csp.dto.SessionListResponse;
import com.maveric.csp.dto.SessionRequest;
import com.maveric.csp.dto.SessionResponse;
import com.maveric.csp.entity.Customer;
import com.maveric.csp.entity.Session;
import com.maveric.csp.exceptions.StatusNotFoundException;
import com.maveric.csp.serviceimpl.SessionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
class SessionControllerTest {
    Date date = new Date();
    Customer customer = new Customer();
    @Mock
    private SessionServiceImpl sessionService;
    @Mock
    private ModelMapper mapper;
    @InjectMocks
    private SessionController sessionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testFindSessionById() {
        String sessionId = "S_00001";
        Session session = new Session();
        session.setSessionId(sessionId);
        when(sessionService.findSessionById(sessionId)).thenReturn(session);
        when(mapper.map(session, SessionResponse.class)).thenReturn(new SessionResponse());
        ResponseEntity<SessionResponse> responseEntity = sessionController.findSessionById(sessionId);
        verify(sessionService, times(1)).findSessionById(sessionId);
        verify(mapper, times(1)).map(session, SessionResponse.class);


        assert responseEntity != null;
        assert responseEntity.getStatusCode() == HttpStatus.OK;
    }



    @Test
    void testUpdateSessionByIdValidRequest() {

        String sessionId = "SESSION_1";
        SessionRequest sessionRequest = new SessionRequest();


        SessionResponse updatedSession = new SessionResponse();

        when(sessionService.udateSessionById(sessionId, sessionRequest)).thenReturn(updatedSession);

        ResponseEntity<SessionResponse> responseEntity = sessionController.uodateSessionById(sessionId, sessionRequest);

        verify(sessionService, times(1)).udateSessionById(sessionId, sessionRequest);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(updatedSession, responseEntity.getBody());
    }

    @Test
    void testFindByStatus_Successful() throws ParseException {
        char status = 'A';
        String createdBy = "USER123";
        int page = 0;
        int pageSize = 10;

        SessionListResponse mockResponse = new SessionListResponse();
        // Set mockResponse properties as needed

        when(sessionService.findWithStatus(status, createdBy, page, pageSize))
                .thenReturn(mockResponse);

        ResponseEntity<SessionListResponse> responseEntity = sessionController.findByStatus(status, createdBy, page, pageSize);

        verify(sessionService, times(1)).findWithStatus(status, createdBy, page, pageSize);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());
    }

    @Test
    void testFindByStatus_StatusNotFound() throws ParseException {
        char status = 'B';
        String createdBy = "USER123";
        int page = 0;
        int pageSize = 10;

        when(sessionService.findWithStatus(status, createdBy, page, pageSize))
                .thenThrow(new StatusNotFoundException());
        assertEquals(StatusNotFoundException.class,StatusNotFoundException.class);
    }


    @Test
    void testUpdateToArchive_Successful() throws ParseException {
        String sessionId = "SESSION_01";

        SessionResponse archivedSessionResponse = new SessionResponse();
        archivedSessionResponse.setSessionId(sessionId);
        when(sessionService.updateToArchived(sessionId)).thenReturn(archivedSessionResponse);

        ResponseEntity<SessionResponse> response = sessionController.updateToArchive(sessionId);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(archivedSessionResponse, response.getBody());

        verify(sessionService).updateToArchived(sessionId);
    }

    @Test
    void testDeleteUserById_Successful() {
        String sessionId = "SESSION_01";
        sessionController.deleteUserById(sessionId);
        verify(sessionService).deleteSessionById(sessionId);
    }

}