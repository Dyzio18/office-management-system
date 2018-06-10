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
    @NotEmpty(message = "*Podaj nazwę usługi")
    private String i_name;

    @Column(name = "service_date")
    @NotEmpty(message = "*Podaj date usługi")
    private String i_date;

    @Column(name="service_case_id")
    @NotNull(message = "*Podaj wartość")
    private int i_case_id;


    @Column(name="service_price")
    @NotNull(message = "*Podaj wartość")
    private BigDecimal i_price;

    @Column(name="service_inner_price")
    @NotNull(message = "*Podaj wartość")
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

    public String getI_date() {
        return i_date;
    }

    public void setI_date(String i_date) {
        this.i_date = i_date;
    }

    public int getI_case_id() {
        return i_case_id;
    }

    public void setI_case_id(int i_case_id) {
        this.i_case_id = i_case_id;
    }
}
