/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.*;

/**
 *
 * @author x00131205
 */
@Entity
@Table(name = "Ticket")
@DiscriminatorValue( value="Premium" )
@SuppressWarnings("SerializableClass")
public class LuxuriousTicket extends Ticket {
    
}
