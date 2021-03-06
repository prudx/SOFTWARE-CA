package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Ticket")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn (name ="Ticket Type")
@SuppressWarnings("SerializableClass")
@SequenceGenerator(name = "tid_seq", initialValue = 10, allocationSize = 1)
public class Ticket {

    //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    //Date date = new Date();
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tid_seq")
    private int ticket_ID;
    private String depDate;         
    private String returnDate;
    
    @ManyToOne()
    @JoinColumn(name = "route_id") 
    private Routes route_id;
    @ManyToMany(mappedBy = "ticketList")
    private List<User> ulist = new ArrayList<>();

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

    public List<User> getUlist() {
        return ulist;
    }

    public void setUlist(List<User> ulist) {
        this.ulist = ulist;
    }
    
    public void addUserToBooking(User u) {
        ulist.add(u);
        u.getTicketList().add(this);
    }
    
    public void removeUserFromBooking(User u){
        ulist.remove(u);
        u.getTicketList().remove(this);
    }
    
    @Override
    public String toString() {
        String s = "";
        return s + "Ticket ID: "+ticket_ID + " Departing on: " +depDate +", Returning on: "+returnDate+"," +route_id;
    }
    
//    public String toString() {
//        String s = "------------------------------------\n";
//        String d = String.format("%1$s %2$tB %2$td, %2$tY", " Departing on:", depDate +" ");
//        String r = String.format("%1$s %2$tB %2$td, %2$tY", " Returning on:", returnDate +" ");
//        return s + "Ticket ID: "+ticket_ID + d + r;
//    }
}
