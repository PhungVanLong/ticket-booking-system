package project.intern.ticket_booking_system.dto;

import lombok.Data;

@Data
public class UpdateCustomerRequest {
    private String name;
    private String email;
    private String password;

}
