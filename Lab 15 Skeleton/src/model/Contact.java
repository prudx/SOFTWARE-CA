package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "CONTACTS")
@SuppressWarnings("SerializableClass")
@SequenceGenerator(name = "cid_seq", initialValue = 1, allocationSize = 1)
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cid_seq")
    private int id;
    private String cname;         
    private String address;
    private String pNumber;  // Person's phone number
    private String email;

    @ManyToMany(mappedBy = "clist")
    private List<AddressBookOwner> olist = new ArrayList<>();

    public Contact() {
    }

    public Contact(String n, String a, String pn, String e) {
        cname = n;
        address = a;
        pNumber = pn;
        email = e;
    }

    public void setName(String n) {
        cname = n;
    }

    public void setAddress(String a) {
        address = a;
    }

    public void setEmail(String e) {
        email = e;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setPhoneNumber(String pn) {
        pNumber = pn;
    }

    public String getName() {
        return cname;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public int getID() {
        return id;
    }

    public String getPhoneNumber() {
        return pNumber;
    }

    public void addStaff(AddressBookOwner s) {
        olist.add(s);
        s.getClist().add(this);
    }

    @Override
    public String toString() {
        String s = "*************************************************";
        return s + "\n" + "Contact: " + "Id: " + id + ", Name: " + cname + ", Address: " + address + ", Number: " 
                + pNumber + ", Email: " + email + "\n" + s;
    }
}
