package com.example.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="client_id")
    public int id;

    @Column(name = "client_name")
    @NotEmpty(message = "*Prosze podaj imie")
    private String c_name;

    @Column(name = "client_surname")
    @NotEmpty(message = "*Prosze podaj nazwisko")
    private String c_surname;

    @Column(name="client_phone")
    @NotNull
    private String c_phone;


    public int getId() {
        return id;
    }
    public void setId(int c_id) { this.id = c_id; }

    public String getC_name() {
        return c_name;
    }
    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_surname() {
        return c_surname;
    }
    public void setC_surname(String c_surname) {
        this.c_surname = c_surname;
    }

    public String getC_phone() {
        return c_phone;
    }
    public void setC_phone(String c_phone) {
        this.c_phone = c_phone;
    }

}
