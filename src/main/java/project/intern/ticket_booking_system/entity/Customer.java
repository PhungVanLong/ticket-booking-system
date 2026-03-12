package project.intern.ticket_booking_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.intern.ticket_booking_system.entity.enums.Role;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private  String name;

    @Column(unique = true,nullable = false)
    private String email;
    @Column(nullable = false,length = 60)
    private  String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;




}
