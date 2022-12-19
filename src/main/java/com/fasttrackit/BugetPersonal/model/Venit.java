package com.fasttrackit.BugetPersonal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Entity
@Data
public class Venit {

    @Id
    @GeneratedValue
    private long id;
    @Column
    private double valoare;
    @Column
    private Date data;
    @Column
    private TipVenit tip;

    @OneToMany(mappedBy = "venit", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Cheltuiala> cheltuieli;

    public Venit(long id, double valoare, Date data, TipVenit tip) {
        this.id = id;
        this.valoare = valoare;
        this.data = data;
        this.tip = tip;
    }
}
