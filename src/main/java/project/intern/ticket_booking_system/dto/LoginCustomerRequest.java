package project.intern.ticket_booking_system.dto;

import lombok.Data;

@Data
public class LoginCustomerRequest {
    private String username;
    private String password;
}
