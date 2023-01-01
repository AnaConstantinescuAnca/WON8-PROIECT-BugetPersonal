package com.fasttrackit.BugetPersonal.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@Entity
@Data
@NoArgsConstructor
public class Cheltuiala {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private double valoare;
    @Column
    private Date data;
    @Column
    private TipCheltuiala tip;

}
