package project.intern.ticket_booking_system.mappers;

import org.mapstruct.Mapper;
import project.intern.ticket_booking_system.dto.CustomerDto;
import project.intern.ticket_booking_system.dto.RegisterCustomerRequest;
import project.intern.ticket_booking_system.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto toDto(Customer customer);

    Customer toEntity(RegisterCustomerRequest registerCustomerRequest);
}
