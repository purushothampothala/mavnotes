package com.maveric.csp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor

public class SessionListResponse {
    private List<SessionResponse> content;
    private long totalElements;

    public SessionListResponse(List<SessionResponse> content, long totalElements) {
        this.content = content;
        this.totalElements = totalElements;
    }
}
