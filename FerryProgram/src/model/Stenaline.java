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
@DiscriminatorValue (value = "1st Class")
public class Stenaline extends Ferry {
    
}
