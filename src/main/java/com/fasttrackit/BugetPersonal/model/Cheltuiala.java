package com.fasttrackit.BugetPersonal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@Entity
@Data
@NoArgsConstructor
public class Cheltuiala {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private double valoare;
    @Temporal(value = TemporalType.DATE)
    @Column
    private Date data;
    @Column
    private TipCheltuiala tip;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Venit venit;

    public Cheltuiala(int id, double valoare, Date data, TipCheltuiala tip) {
        this.id = id;
        this.valoare = valoare;
        this.data = data;
        this.tip = tip;
    }


}
