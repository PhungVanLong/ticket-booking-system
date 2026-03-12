
package project.intern.ticket_booking_system.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id ;

    private  String seat_number;


    @ManyToOne
   @JoinColumn(name = "room_id")
    private  Room room;
}
