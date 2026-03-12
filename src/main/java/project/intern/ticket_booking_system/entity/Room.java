package project.intern.ticket_booking_system.entity;

import jakarta.persistence.*;
import project.intern.ticket_booking_system.entity.enums.Type;

import java.util.List;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    private String name;

    private int totalSeats;

    @OneToMany (mappedBy = "room",cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Seat> seats;

  @Enumerated(value =EnumType.STRING)
    private Type type;

}
