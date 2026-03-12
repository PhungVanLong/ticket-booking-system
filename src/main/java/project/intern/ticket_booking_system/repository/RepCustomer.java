package project.intern.ticket_booking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.intern.ticket_booking_system.entity.Customer;

@Repository
public interface RepCustomer extends JpaRepository<Customer,Long>{


}
