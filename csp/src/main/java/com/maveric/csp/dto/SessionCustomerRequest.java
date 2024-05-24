package com.maveric.csp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionCustomerRequest {
    @NotBlank(message = "customer id is required")
    private long id;
}
