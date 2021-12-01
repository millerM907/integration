package com.mike.integration.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="humidity")
public class Humidity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name="date")
    private LocalDateTime date;
    @Column(name="value")
    private int value;

    public Humidity() {
    }

    public Humidity(LocalDateTime date, int value) {
        this.date = date;
        this.value = value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
