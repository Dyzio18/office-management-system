package com.example.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;

/**
  `case_id` int(11) NOT NULL AUTO_INCREMENT,
  `client_name` varchar(255) NOT NULL,
  `client_surname` varchar(255) NOT NULL,
  `case_date` varchar(32) NULL,
  `case_time` varchar(32) NULL,
  `case_type` varchar(255) NULL,
  `case_lawyer` int(11) NOT NULL,
  `case_note`  varchar(32000) NULL,
  `case_price` decimal(5,2)  NULL,
*/

@Entity
@Table(name = "`case`")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="`case_id`")
    public Long id;

    @Column(name = "`client_name`")
    @NotEmpty(message = "*Podaj imie klienta")
    public String clientName;

    @Column(name = "`client_surname`")
    @NotEmpty(message = "*Podaj nazwisko klienta")
    public String clientSurname;

    @Column(name = "`case_date`")
    @NotEmpty(message = "*Podaj date")
    public String caseDate;

    @Column(name = "`case_time`")
    @NotEmpty(message = "*Podaj godzine")
    public String caseTime;

    @Column(name = "`case_type`")
    public String caseType;

    @Column(name="`case_lawyer`")
    public int lawyerId;

    @Column(name = "`case_note`")
    @NotEmpty(message = "*Podaj opis sprawy")
    public String caseNote;

    @Column(name="`case_price`")
    @NotNull(message = "*Podaj cenę")
    public BigDecimal casePrice;

    public Long getCaseId() {
        return id;
    }

    public void setCaseId(Long i_id) {
        this.id = i_id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String i_name) {
        this.clientName = i_name;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getCaseDate() {
        return caseDate;
    }

    public void setCaseDate(String caseDate) {
        this.caseDate = caseDate;
    }

    public String getCaseTime() {
        return caseTime;
    }

    public void setCaseTime(String caseTime) {
        this.caseTime = caseTime;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public int getLawyerId() {
        return lawyerId;
    }

    public void setLawyerId(int lawyerId) {
        this.lawyerId = lawyerId;
    }

    public String getCaseNote() {
        return caseNote;
    }

    public void setCaseNote(String caseNote) {
        this.caseNote = caseNote;
    }

    public BigDecimal getCasePrice() {
        return casePrice;
    }

    public void setCasePrice(BigDecimal casePrice) {
        this.casePrice = casePrice;
    }

    public static Comparator<Case> getComparator() {
        return new Comparator<Case>(){
            @Override
            public int compare(Case c1, Case c2)
            {
                String c1Date = c1.getCaseDate();
                String c2Date = c2.getCaseDate();
                if (c1Date.equals(c2Date)) {
                    return c1.getCaseTime().compareTo(c2.getCaseTime());
                }
                return c1Date.compareTo(c2Date);
            }
        };
    }
}
