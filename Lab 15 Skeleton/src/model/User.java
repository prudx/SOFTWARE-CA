package model;

import java.util.List;
import javax.persistence.*;
@Entity
@Table(name = "Users")
@SuppressWarnings("SerializableClass")
public class User {
    @Id
    private int id;
    private String username;
    private String name;
    private String dob;
    
    @ManyToMany (cascade = CascadeType.PERSIST)
    @JoinTable(name="Booking",
    joinColumns = @JoinColumn(name="user_id"),
    inverseJoinColumns=@JoinColumn(name="ticket_id"))
    private List<Ticket> ticketList;

    public User() {        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ticket getContact(int i) {
        return ticketList.get(i);
    }

    public String getOwner() {
        return name;
    }

    public List<Ticket> getClist() {
        return ticketList;
    }
}
