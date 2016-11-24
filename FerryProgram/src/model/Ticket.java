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

    public Ticket(String depDate, String returnDate) {
        this.depDate = depDate;
        this.returnDate = returnDate;
    }

//    public Ticket(String depDate, String returnDate, int route_id) {
//        this.depDate = depDate;
//        this.returnDate = returnDate;
//        //this.route_id = route_id
//    }

    
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

    public void setR(Routes r) {
        this.route_id = r;
    }
    
    
    @Override
    public String toString() {
        String s = "------------------------------------\n";
        return s + "Ticket ID: "+ticket_ID + " Departing on: " +depDate +", Returning on: "+returnDate ;
    }
    
//    public String toString() {
//        String s = "------------------------------------\n";
//        String d = String.format("%1$s %2$tB %2$td, %2$tY", " Departing on:", depDate +" ");
//        String r = String.format("%1$s %2$tB %2$td, %2$tY", " Returning on:", returnDate +" ");
//        return s + "Ticket ID: "+ticket_ID + d + r;
//    }
}
