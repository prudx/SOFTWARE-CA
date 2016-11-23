/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author x00131205
 */
@Entity
@Table(name = "Routes")
@SuppressWarnings("SerializableClass")
public class Routes {
    
    @Id
    private int route_id;

    @OneToMany(mappedBy = "route_id")
    private List<Ticket> tlist = new ArrayList<>();
    
//    @OneToOne(mappedBy="ferry_id")
//    private Ferry ferry_id;
    
    public Routes() {
    }
    
    
}
