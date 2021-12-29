package com.parkly.backend.bizz.domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PLACEHOLDER")
public class ParkingSlot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
}
