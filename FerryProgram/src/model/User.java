package model;

import java.util.List;
import javax.persistence.*;
@Entity
@Table(name = "Users")
@SuppressWarnings("SerializableClass")
@SequenceGenerator(name = "uid_seq", sequenceName="uid_seq", allocationSize = 1)
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "uid_seq")
    private int user_ID;
    private String username;
    private String password;
    private String name;
    private String dob;
    

    @ManyToMany (cascade = CascadeType.PERSIST)
    @JoinTable(name="Booking",
    joinColumns = @JoinColumn(name="user_id"),
    inverseJoinColumns=@JoinColumn(name="ticket_id"))
    private List<Ticket> ticketList;

    public User() {        
    }

    public User(String username, String password, String name, String dob) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.dob = dob;
    }
    
    public int getId() {
        return user_ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
//    public Ticket getUserTicketlist(int i) {
//        return ticketList.get(i);
//    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    
    
    public String getName() {
        return name;
    }

    public List<Ticket> getFullTicketList() {
        return ticketList;
    }
    
}
