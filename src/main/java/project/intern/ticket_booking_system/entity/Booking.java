package project.intern.ticket_booking_system.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import project.intern.ticket_booking_system.entity.enums.BookingStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
   final private  BookingStatus bookingStatus = BookingStatus.HOLDING; // Set default value

    @CreationTimestamp // Automatically sets the time when record is created
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(  name = "customer_id" )
    private Customer customer;
    // Default Constructor

    @OneToMany(mappedBy = "booking",cascade = CascadeType.ALL)
    private List<ShowtimeSeat>seats;

    public Booking() {}
}
