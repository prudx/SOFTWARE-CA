/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dbService.JPAService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author x00131205
 */
@Entity
@Table(name = "Route")
@SuppressWarnings("SerializableClass")
public class Routes {
    
    
    
    @Id
    private int route_id;
    private String route_name;
    private int route_time;

    @OneToMany(mappedBy = "route_id")
    private List<Ticket> tlist = new ArrayList<>();
    
//    @OneToOne(mappedBy="ferry_id")
//    private Ferry ferry_id;
    
    public Routes() {
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }

    public int getRoute_time() {
        return route_time;
    }

    public void setRoute_time(int route_time) {
        this.route_time = route_time;
    }

    public List<Ticket> getTlist() {
        return tlist;
    }

    public void addTicket(Ticket t){
        this.tlist.add(t);
        t.setR(this);
    }
    
    public void setTlist(List<Ticket> tlist) {
        this.tlist = tlist;
    }

    @Override
    public String toString() {
        
        return " Route: " + route_name + ", Route duration: " + route_time;
    }
    
    
    
}
