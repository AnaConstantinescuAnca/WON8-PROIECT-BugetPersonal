package com.fasttrackit.BugetPersonal.service;

import com.fasttrackit.BugetPersonal.model.TipVenit;
import com.fasttrackit.BugetPersonal.model.Venit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public interface VenitRepository extends JpaRepository<Venit, Integer> {


    @Query("SELECT v FROM Venit v WHERE (v.data=:data or :data is null)" +
            " and (v.valoare=:valoare or :valoare is null)" +
            " and (v.tip=:tip or :tip is null)")
    List<Venit> getByValoareTipDataVenit(@Param("valoare") Double valoare,
                                      @Param("tip") TipVenit tip,
                                      @Param("data") Date data);

    @Query("SELECT v FROM Venit v WHERE v.tip=:tip or :tip is null")
    List<Venit> getVenituriByTip(@Param("tip") TipVenit tip);

    @Query("SELECT v FROM Venit v WHERE v.data=:data ")
    List<Venit> getVenituriByData(@Param("data") Date data);


}
