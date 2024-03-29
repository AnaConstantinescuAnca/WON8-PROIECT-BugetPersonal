package com.fasttrackit.BugetPersonal.service;

import com.fasttrackit.BugetPersonal.model.Cheltuiala;
import com.fasttrackit.BugetPersonal.model.TipCheltuiala;

import com.fasttrackit.BugetPersonal.model.TipVenit;
import com.fasttrackit.BugetPersonal.model.Venit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CheltuialaRepository extends JpaRepository<Cheltuiala, Integer> {
    @Query("SELECT c FROM Cheltuiala c WHERE (c.data=:data or :data is null)" +
            " and (c.valoare=:valoare or :valoare is null)" +
            " and (c.tip=:tip or :tip is null)")
    List<Cheltuiala> getByValoareTipDataCheltuiala(@Param("valoare") Double valoare,
                                                   @Param("tip") TipCheltuiala tip,
                                                   @Param("data") Date data);

    @Query("SELECT c FROM Cheltuiala c WHERE c.data=:data")
    List<Cheltuiala> getCheltuieliByData(@Param("data") Date data);

    @Query("SELECT c FROM Cheltuiala c WHERE c.tip =:tip")
    List<Cheltuiala> getCheltuieliTip(@Param("tip") TipCheltuiala tip);

    @Query(nativeQuery = true, value = "SELECT * FROM Cheltuiala c WHERE" +
            " substring(to_char(c.data, 'yyyy-MM-dd'),1,7)=:anLuna" +
            " and c.tip=:tip")
    List<Cheltuiala> getCheltuieliByAnLunaTip(@Param("anLuna") String anLuna,
                                            @Param("tip") TipCheltuiala tip);

}
