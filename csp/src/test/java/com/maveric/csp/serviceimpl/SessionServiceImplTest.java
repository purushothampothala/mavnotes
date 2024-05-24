package com.maveric.csp.serviceimpl;

import com.maveric.csp.dto.SessionCustomerRequest;
import com.maveric.csp.dto.SessionListResponse;
import com.maveric.csp.dto.SessionRequest;
import com.maveric.csp.dto.SessionResponse;
import com.maveric.csp.entity.Customer;
import com.maveric.csp.entity.Session;
import com.maveric.csp.entity.SessionHistory;
import com.maveric.csp.exceptions.SessionNotFoundException;
import com.maveric.csp.exceptions.StatusNotFoundException;
import com.maveric.csp.exceptions.UserNotFoundException;
import com.maveric.csp.repository.CustomerRepository;
import com.maveric.csp.repository.SessionHistoryRepository;
import com.maveric.csp.repository.SessionRepository;
import com.maveric.csp.service.SessionService;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
//@RunWith(SpringRunner.class)
@SpringBootTest
 class SessionServiceImplTest {

    @InjectMocks
    private SessionServiceImpl sessionService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private SessionRepository sessionRepository;
    @Mock
    private SessionHistoryRepository sessionHistoryRepository;
    @Mock
    private ModelMapper mapper;


    @BeforeEach
    public void setUp() throws Exception {
        mock(CustomerRepository.class);
        MockitoAnnotations.openMocks(this);

    }


    @Test
    void testCreateSession_Successful() throws ParseException {

        Customer existingCustomer = new Customer();
        existingCustomer.setId(1L);
        SessionRequest sessionRequest = new SessionRequest();
        //sessionRequest.setCustomers(new SessionCustomerRequest());
        sessionRequest.setCustomerId(1L);
        Session session = new Session();
        session.setSessionId("SESSION_01");
        SessionResponse expectedResponse = new SessionResponse();
        expectedResponse.setSessionId("SESSION_52");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(existingCustomer));
        when(mapper.map(sessionRequest, Session.class)).thenReturn(session);
        when(sessionRepository.save(session)).thenReturn(session);
        when(mapper.map(session, SessionResponse.class)).thenReturn(expectedResponse);
        SessionResponse actualResponse = sessionService.createSession(sessionRequest);
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void testCreateSession_CustomerNotFound() {
        SessionRequest sessionRequest = new SessionRequest();
        //sessionRequest.setCustomers(new SessionCustomerRequest());
        sessionRequest.setCustomerId(1L);

        when(customerRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> sessionService.createSession(sessionRequest));

    }

    @Test
    void testFindSessionById_Successful() {
        String sessionId = "SESSION_01";
        Session expectedSession = new Session();
        expectedSession.setSessionId(sessionId);
        when(sessionRepository.findBySessionId(sessionId)).thenReturn(Optional.of(expectedSession));
        Session actualSession = sessionService.findSessionById(sessionId);
        assertEquals(expectedSession, actualSession);
    }

    @Test
    void testFindSessionById_SessionNotFound() {
        Mockito.when(sessionRepository.findBySessionId(Mockito.anyString())).thenReturn(Optional.empty());
        assertThrows(SessionNotFoundException.class, () -> {
            sessionService.findSessionById("SESSION_01");
        });
    }

    @Test
    void testUpdateSessionById_Success() {
        SessionRequest request = new SessionRequest();
        request.setSessionName("NewSession");
        request.setRemarks(" remarks");
        request.setCreatedBy("Purushotham");
        // Create existing session for the mock repository
        Session existingSession = new Session();
        existingSession.setSessionId("SESSION_52");
        existingSession.setSessionName("Session");
        existingSession.setRemarks("No-remarks");
        existingSession.setCreatedBy("Purushotham Pothala");
        existingSession.setUpdatedOn(new Date());
        when(sessionRepository.findBySessionId("SESSION_52")).thenReturn(Optional.of(existingSession));
        SessionResponse mappedResponse = new SessionResponse(); // You need to create a SessionResponse object here
        when(mapper.map(any(Session.class), eq(SessionResponse.class))).thenReturn(mappedResponse);
        SessionResponse result = sessionService.udateSessionById("SESSION_52", request);

        // Assertions
        verify(sessionRepository).save(existingSession);
        assertEquals(request.getSessionName(), existingSession.getSessionName());
        assertEquals(request.getRemarks(), existingSession.getRemarks());
        assertEquals(request.getCreatedBy(), existingSession.getCreatedBy());
        assertNotNull(existingSession.getUpdatedOn());
        assertEquals(mappedResponse, result);
    }

    @Test
    void testUpdateSessionById_SessionNotFound() {
        Mockito.when(sessionRepository.findBySessionId(Mockito.anyString())).thenReturn(Optional.empty());
        assertThrows(SessionNotFoundException.class, () -> {
            sessionService.udateSessionById("SESSION_01", new SessionRequest());
        });
    }


    @Test
    void testDeleteSessionById_Success() {
        Session session = new Session();
        session.setSessionId("SESSION_01");
        session.setSessionName("Session");
        session.setRemarks("No remarks");
        session.setStatus('A');
        session.setCreatedOn(new Date());
        session.setCreatedBy("Purushotham");
        session.setUpdatedOn(new Date());
        session.setCustomer(new Customer(1L, "Purushotham"));
        when(sessionRepository.findBySessionId("SESSION_01")).thenReturn(Optional.of(session));
        Session result = sessionService.deleteSessionById("SESSION_01");
        verify(sessionRepository).save(session);
        verify(sessionHistoryRepository).save(any(SessionHistory.class));
        assertEquals(session, result);
    }

    @Test
    void testDeleteSessionById_SessionNotFound() {
        Mockito.when(sessionRepository.findBySessionId(Mockito.anyString())).thenReturn(Optional.empty());
        assertThrows(SessionNotFoundException.class, () -> {
            sessionService.deleteSessionById("SESSION_01");
        });
    }
//    @Test
//    void testFindWithStatus_Successful() throws ParseException {
//        char status = 'A';
//        String createdBy="Purushotham";
//        int page = 0;
//        int pageSize = 10;
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//        Date currentDate = new Date();
//        String formattedDate = dateFormat.format(currentDate);
//        Session session = new Session();
//        session.setSessionId("SESSION_01");
//        session.setSessionName("Session");
//        session.setRemarks("No remarks");
//        session.setStatus('A');
//        session.setCreatedOn(new Date());
//        session.setCreatedBy("Purushotham");
//        session.setUpdatedOn(new Date());
//        session.setUpdatedBy("Purushotham");
//        session.setCustomers(new Customer(1L, "Purushotham"));
//
//        Session session1 = new Session();
//        session1.setSessionId("SESSION_02");
//        session1.setSessionName("Session");
//        session1.setRemarks("No remarks");
//        session1.setStatus('A');
//        session1.setCreatedOn(new Date());
//        session1.setCreatedBy("Purushotham");
//        session1.setUpdatedOn(new Date());
//        session1.setUpdatedBy("Purushotham");
//        session1.setCustomers(new Customer(1L, "Purushotham"));
//
//        List<Session> sessions = new ArrayList<>();
//        sessions.add(session);
//        sessions.add(session1);
//
//        when(sessionRepository.findByStatus(status,createdBy,PageRequest.of(page, pageSize)))
//                .thenReturn(sessions);
//        when(mapper.map(any(Session.class), eq(SessionResponse.class)))
//                .thenReturn(new SessionResponse());
//
//
//        List<SessionResponse> result = sessionService.findWithStatus(status,createdBy, page, pageSize);
//        assertEquals(2, result.size());
//        verify(sessionRepository).findByStatus(status,createdBy, PageRequest.of(page, pageSize));
//        verify(mapper, times(2)).map(any(Session.class), eq(SessionResponse.class));
//    }
    @Test
    public void testFindWithStatus_Successful() throws ParseException {
        char status = 'A';
        String createdBy = "Purushotham";
        int page = 0;
        int pageSize = 10;

        List<Session> sessions = new ArrayList<>();
        Session session1 = new Session();

        sessions.add(session1);

        Page<Session> sessionPage = new PageImpl<>(sessions);
        when(sessionRepository.findByStatus(eq(status), eq(createdBy), any(PageRequest.class)))
                .thenReturn(sessionPage);

        when(mapper.map(eq(session1), eq(SessionResponse.class))).thenReturn(new SessionResponse());

        SessionListResponse result = sessionService.findWithStatus(status, createdBy, page, pageSize);

        assertNotNull(result);
        assertEquals(sessions.size(), result.getContent().size());

        verify(sessionRepository).findByStatus(eq(status), eq(createdBy), any(PageRequest.class));
        verify(mapper, times(sessions.size())).map(any(Session.class), eq(SessionResponse.class));
    }
    @Test
     void testFindWithStatus_StatusNotFound() throws ParseException {
        char status = 'A';
        String createdBy = "USER123";
        int page = 0;
        int pageSize = 10;


        when(sessionRepository.findByStatus(eq(status), eq(createdBy), any(PageRequest.class)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));


        assertThrows(StatusNotFoundException.class,
                () -> sessionService.findWithStatus(status, createdBy, page, pageSize));

        verify(sessionRepository).findByStatus(eq(status), eq(createdBy), any(PageRequest.class));

        verify(mapper, never()).map(any(Session.class), eq(SessionResponse.class));
    }
    @Test
    void testFindWithStatus_NegativePageAndPageSize() {
        char status = 'A';
        String createdBy="Purushotham";
        int page = -1; // Negative page
        int pageSize = -10; // Negative pageSize
        assertThrows(IllegalArgumentException.class, () -> {
            sessionService.findWithStatus(status,createdBy, page, pageSize);
        });
        verifyNoInteractions(sessionRepository);
    }




//    @Test
//    void testUpdateToArchived_Successful() throws ParseException {
//        String sessionId = "SESSION_01";
//        Date currentDate = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ENGLISH);
//        Date updatedOn = sdf.parse("2023-08-17 14:45:35.000"); // Replace with your actual date string
//        Session session = new Session();
//        session.setSessionId(sessionId);
//        session.setUpdatedOn(updatedOn);
//        session.setStatus('A');
//        Session archivedSession = new Session();
//        archivedSession.setSessionId(sessionId);
//        archivedSession.setUpdatedOn(currentDate);
//        archivedSession.setStatus('X');
//        when(sessionRepository.findBySessionId(sessionId)).thenReturn(Optional.of(session));
//        when(sessionRepository.save(any(Session.class))).thenReturn(archivedSession);
//        when(mapper.map(archivedSession, SessionResponse.class)).thenReturn(new SessionResponse());
//        SessionResponse result = sessionService.updateToArchived(sessionId);
//        assertNotNull(result);
//        verify(sessionRepository).findBySessionId(sessionId);
//        verify(sessionRepository).save(session);
//        verify(mapper).map(archivedSession, SessionResponse.class);
//    }


    @Test
    void testUpdateToArchived_SessionNotFound() {
        String sessionId = "SESSION_01";
        when(sessionRepository.findBySessionId(sessionId)).thenReturn(Optional.empty());
        assertThrows(SessionNotFoundException.class, () -> {
            sessionService.updateToArchived(sessionId);
        });
        verify(sessionRepository).findBySessionId(sessionId);
        verify(sessionRepository, never()).save(any(Session.class));
        verify(mapper, never()).map(any(Session.class), eq(SessionResponse.class));
    }
}
