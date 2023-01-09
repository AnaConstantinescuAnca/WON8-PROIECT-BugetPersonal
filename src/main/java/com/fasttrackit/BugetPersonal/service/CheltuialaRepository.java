package com.fasttrackit.BugetPersonal.service;

import com.fasttrackit.BugetPersonal.model.Cheltuiala;
import com.fasttrackit.BugetPersonal.model.TipCheltuiala;
import com.fasttrackit.BugetPersonal.model.TipVenit;
import com.fasttrackit.BugetPersonal.model.Venit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Repository
public interface CheltuialaRepository extends JpaRepository<Cheltuiala, Integer> {
    @Query("SELECT c FROM Cheltuiala c WHERE (c.data=:data or :data is null)" +
            " and (c.valoare=:valoare or :valoare is null)"+
            " and (c.tip=:tip or :tip is null)")
    List<Cheltuiala> getByValoareTipDataCheltuiala(@Param("valoare") Double valoare,
                                                @Param("tip") TipCheltuiala tip,
                                                @Param("data") Date data);

    @Query("SELECT c FROM Cheltuiala c WHERE c.data=:data")
    List<Venit> getCheltuieliByData(@Param("data") Date data);
}
