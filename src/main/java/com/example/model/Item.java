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
    public int id;

    @Column(name = "service_name")
    @NotEmpty(message = "*Please provide name")
    private String i_name;

    @Column(name="service_price")
    @NotNull
    private BigDecimal i_price;

    @Column(name="service_inner_price")
    @NotNull
    private BigDecimal inner_price;

    public int getId() {
        return id;
    }

    public void setId(int i_id) {
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

    public BigDecimal getInner_price() {
        return inner_price;
    }

    public void setInner_price(BigDecimal inner_price) {
        this.inner_price = inner_price;
    }
}
