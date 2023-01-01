package com.fasttrackit.BugetPersonal.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Entity
//@Data
@NoArgsConstructor
public class Venit {

    @Id
    @GeneratedValue
    private long id;
    @Column
    private double valoare;
    @Column
    private Date dataVenit;
    @Column
    private TipVenit tip;

    @OneToMany(mappedBy = "venit", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Cheltuiala> cheltuieli;

//    public Venit(long id, double valoare, Date data, TipVenit tip) {
//        this.id = id;
//        this.valoare = valoare;
//        this.data = data;
//        this.tip = tip;
//    }
}
