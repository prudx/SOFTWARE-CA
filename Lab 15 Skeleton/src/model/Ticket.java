package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Ticket")
@SuppressWarnings("SerializableClass")
@SequenceGenerator(name = "cid_seq", initialValue = 1, allocationSize = 1)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cid_seq")
    private int id;
    private String ticket_type;         
    private double ticket_cost;
    @ManyToOne()
    @JoinColumn(name = "route_id") 
    private int route_id;

    @ManyToMany(mappedBy = "ticketList")
    private List<User> olist = new ArrayList<>();

    public Ticket() {
    }

    public Ticket(int id, String ticket_type, double ticket_cost) {
        this.id = id;
        this.ticket_type = ticket_type;
        this.ticket_cost = ticket_cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTicket_type() {
        return ticket_type;
    }

    public void setTicket_type(String ticket_type) {
        this.ticket_type = ticket_type;
    }

    public double getTicket_cost() {
        return ticket_cost;
    }

    public void setTicket_cost(double ticket_cost) {
        this.ticket_cost = ticket_cost;
    }

    public List<User> getOlist() {
        return olist;
    }

    public void setOlist(List<User> olist) {
        this.olist = olist;
    }

    

    public void addStaff(User s) {
        olist.add(s);
        s.getClist().add(this);
    }

    @Override
    public String toString() {
        String s = "*************************************************";
        return s + "\n" + "Ticket type: " +ticket_type +", Ticket cost: "+ticket_cost ;
    }
}
