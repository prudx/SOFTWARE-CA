package model;

import java.util.List;
import javax.persistence.*;
@Entity
@Table(name = "ADDRESSBOOKOWNERS")
@SuppressWarnings("SerializableClass")
public class AddressBookOwner {
    @Id
    private int id;
    private String name;
    @ManyToMany (cascade = CascadeType.PERSIST)
    @JoinTable(name="ContactsAddressBook",
    joinColumns = @JoinColumn(name="ABID"),
    inverseJoinColumns=@JoinColumn(name="CID"))
    private List<Contact> clist;

    public AddressBookOwner() {       
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Contact getContact(int i) {
        return clist.get(i);
    }

    public String getOwner() {
        return name;
    }

    public List<Contact> getClist() {
        return clist;
    }
}
