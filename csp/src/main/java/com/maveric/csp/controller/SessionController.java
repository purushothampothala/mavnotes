package com.maveric.csp.controller;

import com.maveric.csp.dto.SessionListResponse;
import com.maveric.csp.dto.SessionRequest;
import com.maveric.csp.dto.SessionResponse;
import com.maveric.csp.entity.Session;
import com.maveric.csp.repository.CustomerRepository;
import com.maveric.csp.repository.SessionHistoryRepository;
import com.maveric.csp.repository.SessionRepository;
import com.maveric.csp.serviceimpl.SessionServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@Validated
@RequestMapping("/session")
public class SessionController {

        @Autowired
    private SessionServiceImpl sessionService;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private SessionHistoryRepository sessionHistoryRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Value("${defaultPageValue}")
    private int defaultPageValue;
    @Value("${defaultPageSizeValue}")
    private int defaultPageSizeValue;
    /**
     * Handle HTTP POST requests to create a new session.
     *
     * @param sessionRequest The session details provided in the request body.
     * @return A ResponseEntity containing the created session's information in the response body.
     * @throws ParseException If there's an issue parsing date-related information.
     */
    @PostMapping
    public ResponseEntity<SessionResponse> createSession(@RequestBody @Valid  SessionRequest sessionRequest) throws ParseException {
        SessionResponse createdSession = sessionService.createSession(sessionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSession);
    }

    /**
     * Retrieve session details by ID.
     *
     * @param id The ID of the session to retrieve.
     * @return A ResponseEntity containing the session details in the response body.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SessionResponse> findSessionById(@PathVariable String id) {
        Session session = sessionService.findSessionById(id);

         return ResponseEntity.ok(mapper.map(session, SessionResponse.class));
    }

    /**
     * Update session details by ID.
     *
     * @param id The ID of the session to update.
     * @param sessionRequest The updated session details provided in the request body.
     * @return A ResponseEntity containing the updated session details in the response body.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SessionResponse> uodateSessionById(@PathVariable String id, @RequestBody SessionRequest sessionRequest) {
            return new ResponseEntity<>(sessionService.udateSessionById(id,sessionRequest),HttpStatus.CREATED);
        }

    /**
     * Retrieve sessions by status and creator.
     *
     * @param status The status of sessions to retrieve.
     * @param createdBy The creator of sessions to retrieve.
     * @param page The page number for pagination.
     * @param pageSize The page size for pagination.
     * @return A ResponseEntity containing a list of sessions matching the status and creator in the response body.
     */
    @GetMapping("/{status}/{createdBy}")
    public ResponseEntity<SessionListResponse> findByStatus(@PathVariable char status,@PathVariable String createdBy,@RequestParam(defaultValue ="${defaultPageValue}") int page,
                                              @RequestParam(defaultValue = "${defaultPageSizeValue}") int pageSize ) throws ParseException {

        SessionListResponse sessionswithStatus= sessionService.findWithStatus(status,createdBy,page,pageSize);
        return ResponseEntity.ok(sessionswithStatus);
    }
    /**
     * Update a session's status to archived by ID.
     *
     * @param id The ID of the session to update.
     * @return A ResponseEntity containing the archived session details in the response body.
     */
    @PatchMapping("/archived/{id}")
        public ResponseEntity<SessionResponse> updateToArchive(@PathVariable String id) throws ParseException {
       SessionResponse archivedSession= sessionService.updateToArchived(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(archivedSession);
    }
    /**
     * Delete a session by ID.
     *
     * @param id The ID of the session to delete.
     * @return A success message indicating that the session history was deleted successfully.
     */
    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable String id) {
        sessionService.deleteSessionById(id);
        return "session history created successfully";
    }
}