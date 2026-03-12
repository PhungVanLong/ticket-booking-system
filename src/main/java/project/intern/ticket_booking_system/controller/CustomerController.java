package project.intern.ticket_booking_system.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.intern.ticket_booking_system.entity.Customer;
import project.intern.ticket_booking_system.repository.RepCustomer;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final RepCustomer customerRepository;

    @GetMapping("/customer")
    public List<Customer> getCustomer(){
      return   customerRepository.findAll();
    }
}
