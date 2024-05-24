package com.maveric.csp.service;

import com.maveric.csp.dto.SessionListResponse;
import com.maveric.csp.dto.SessionRequest;
import com.maveric.csp.dto.SessionResponse;
import com.maveric.csp.entity.Session;

import java.text.ParseException;

public interface SessionService {


     SessionResponse createSession(SessionRequest sessionRequest) throws ParseException;

     //SessionHistory createSessionHistory(SessionHistory sessionHistory);
     Session findSessionById(String id);

//     SessionListResponse getAllSessions(int page, int pageSize) throws ParseException;

     SessionResponse updateToArchived(String id) throws ParseException;
     SessionResponse udateSessionById(String id, SessionRequest session);
     SessionListResponse findWithStatus(char status, String createdBy, int page, int pageSize) throws ParseException;
     Session deleteSessionById(String id);

    //public List<SessionResponse> getAllSessions() throws ParseException;
}
