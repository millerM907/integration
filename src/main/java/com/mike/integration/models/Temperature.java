package com.mike.integration.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="temperature")
public class Temperature {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name="date")
    private LocalDateTime date;
    @Column(name="value")
    private int value;

    public Temperature() {
    }

    public Temperature(LocalDateTime date, int value) {
        this.date = date;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Temperature{" +
                "id=" + id +
                ", date=" + date +
                ", value=" + value +
                '}';
    }
}
