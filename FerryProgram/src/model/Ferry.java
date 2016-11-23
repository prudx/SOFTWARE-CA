/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author x00131205
 */
import javax.persistence.*;
@Entity
@Table (name = "Ferry")
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn (name = "Type")

@SuppressWarnings ("SerializableClass")
public class Ferry {
    @Id
    private int ferry_id;
    
    @OneToOne(mappedBy="route_id")
    private Routes route_id;
}
