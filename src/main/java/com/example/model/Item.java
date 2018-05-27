package com.example.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
@Table(name = "service")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="service_id")
    private int id;

    @Column(name = "service_name")
    @NotEmpty(message = "*Please provide name")
    private String i_name;

    @Column(name="service_price")
    @NotNull
    private BigDecimal i_price;

    public int getI_id() {
        return id;
    }

    public void setI_id(int i_id) {
        this.id = i_id;
    }

    public String getI_name() {
        return i_name;
    }

    public void setI_name(String i_name) {
        this.i_name = i_name;
    }

    public BigDecimal getI_price() {
        return i_price;
    }

    public void setI_price(BigDecimal i_price) {
        this.i_price = i_price;
    }

}
