package com.parkly.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "PLACEHOLDER")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ParkingSlot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
}
