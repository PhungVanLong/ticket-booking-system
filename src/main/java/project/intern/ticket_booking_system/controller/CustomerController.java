package project.intern.ticket_booking_system.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import project.intern.ticket_booking_system.dto.ChangePassword;
import project.intern.ticket_booking_system.dto.CustomerDto;
import project.intern.ticket_booking_system.dto.RegisterCustomerRequest;
import project.intern.ticket_booking_system.dto.UpdateCustomerRequest;
import project.intern.ticket_booking_system.entity.enums.Role;
import project.intern.ticket_booking_system.mappers.CustomerMapper;
import project.intern.ticket_booking_system.repository.RepCustomer;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {
    private final RepCustomer customerRepository;
    private final CustomerMapper customerMapper;

    @GetMapping("/list")
    public List<CustomerDto> getAllCustomer(
            @RequestHeader(required = false, name = "x-auth-token") String authToken,
            @RequestParam(required = false, defaultValue = "", name = "sort") String sortBy) {
//        System.out.print(authToken);
        if (!Set.of("name", "email", "id").contains(sortBy)) {
            sortBy = "id";
        }
        return customerRepository
                .findAll(Sort.by(sortBy))
                .stream().
                map(customerMapper::toDto).toList();
    }

    @GetMapping("/{id}")
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
        var location = uriBuilder.path("/{id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(location).body(customerDto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody UpdateCustomerRequest request) {
        var customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        customerMapper.update(request, customer);
        customerRepository.save(customer);
        return ResponseEntity.ok(customerMapper.toDto(customer));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        var customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        customerRepository.delete(customer);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(@PathVariable Long id, @RequestBody ChangePassword changePassword) {
        var customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        if (customer.getPassword().equals(changePassword.getNewPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        customer.setPassword(changePassword.getNewPassword());
        customerRepository.save(customer);
        return ResponseEntity.noContent().build();
    }
}
