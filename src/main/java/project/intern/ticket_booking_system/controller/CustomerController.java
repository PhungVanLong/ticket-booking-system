package project.intern.ticket_booking_system.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import project.intern.ticket_booking_system.dto.CustomerDto;
import project.intern.ticket_booking_system.dto.RegisterCustomerRequest;
import project.intern.ticket_booking_system.entity.enums.Role;
import project.intern.ticket_booking_system.mappers.CustomerMapper;
import project.intern.ticket_booking_system.repository.RepCustomer;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CustomerController {
    private final RepCustomer customerRepository;
    private final CustomerMapper customerMapper;

    @GetMapping("/customer")
    public List<CustomerDto> getAllCustomer(
            @RequestHeader(required = false, name = "x-auth-token") String authToken,
            @RequestParam(required = false, defaultValue = "", name = "sort") String sortBy) {
        System.out.print(authToken);
        if (!Set.of("name", "email").contains(sortBy)) {
            sortBy = "name";
        }
        return customerRepository
                .findAll(Sort.by(sortBy))
                .stream().
                map(customerMapper::toDto).toList();
    }

    @GetMapping("/customer/{id}")
    @SuppressWarnings("NullableProblems")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long id) {
        var customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerMapper.toDto(customer));
    }

    @PostMapping("/register")
    public ResponseEntity<CustomerDto> createCustomer(
            @RequestBody RegisterCustomerRequest registerCustomerRequest,
            UriComponentsBuilder uriBuilder) {
        var customer = customerMapper.toEntity(registerCustomerRequest);
        customer.setRole(Role.ROLE_USER);
        customerRepository.save(customer);
        var customerDto = customerMapper.toDto(customer);
        var location = uriBuilder.path("/customer/{id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(location).body(customerDto);
    }

}
