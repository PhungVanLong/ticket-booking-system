package project.intern.ticket_booking_system.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name ="Movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    private String description ;
    private LocalDateTime durationMins ;
    private String posterUrl ;

}
