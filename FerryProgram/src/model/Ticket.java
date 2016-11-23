package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Ticket")
@SuppressWarnings("SerializableClass")
@SequenceGenerator(name = "tid_seq", initialValue = 10, allocationSize = 1)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tid_seq")
    private int ticket_ID;
    private String depDate;         
    private String returnDate;
    
    @ManyToOne()
    @JoinColumn(name = "route_id") 
    private Routes route_id;

    @ManyToMany(mappedBy = "ticketList")
    private List<User> olist = new ArrayList<>();

    public Ticket() {
    }

    public Ticket(int ticket_ID, String depDate, String returnDate, Routes route_id) {
        this.ticket_ID = ticket_ID;
        this.depDate = depDate;
        this.returnDate = returnDate;
        this.route_id = route_id;
    }

    public int getTicket_ID() {
        return ticket_ID;
    }

    public void setTicket_ID(int ticket_ID) {
        this.ticket_ID = ticket_ID;
    }

    public String getDepDate() {
        return depDate;
    }

    public void setDepDate(String depDate) {
        this.depDate = depDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Routes getRoute_id() {
        return route_id;
    }

    public void setRoute_id(Routes route_id) {
        this.route_id = route_id;
    }


    @Override
    public String toString() {
        String s = "------------------------------------";
        return s + "Ticket ID: "+ticket_ID + "\n" + "Departing on: " +depDate +", Returning on: "+returnDate ;
    }
}
