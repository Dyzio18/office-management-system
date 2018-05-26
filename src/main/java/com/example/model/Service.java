package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="service_id")
    private int s_id;

    @Column(name = "service_name")
    private String s_name;

    @Column(name = "service_price")
    private double s_price;

    public Service()
    {

    }

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public double getS_price() {
        return s_price;
    }

    public void setS_price(double s_price) {
        this.s_price = s_price;
    }

    @Override
    public String toString() {
        return "Service{" +
                "s_id=" + s_id +
                ", s_name='" + s_name + '\'' +
                ", s_price=" + s_price +
                '}';
    }
}
