package com.thoughtworks.parking_lot.model;

import com.sun.javafx.geom.transform.Identity;
import jdk.nashorn.internal.objects.annotations.Getter;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "parking_lot")
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

    @Column
    @Min(value = 0)
    private int capacity;

    @Column
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
