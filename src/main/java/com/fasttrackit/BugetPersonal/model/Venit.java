package com.fasttrackit.BugetPersonal.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
@Entity
//@Data
@NoArgsConstructor
public class Venit {

    @Id
    @GeneratedValue
    private int id;
    @Column
    private double valoare;

    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column
    private Date data;
    @Column
    private TipVenit tip;

    @OneToMany(mappedBy = "venit", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Cheltuiala> cheltuieli;

    public Venit(int id, double valoare, Date data, TipVenit tip) {
        this.id = id;
        this.valoare = valoare;
        this.data = data;
        this.tip = tip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venit venit = (Venit) o;
        return id == venit.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
