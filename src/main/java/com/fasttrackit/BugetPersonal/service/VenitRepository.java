package com.fasttrackit.BugetPersonal.service;

import com.fasttrackit.BugetPersonal.model.Cheltuiala;
import com.fasttrackit.BugetPersonal.model.Venit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface VenitRepository extends JpaRepository<Venit, Integer> {


//    @Query(value = "SELECT id, function('date_format', v.data, '%Y, %m, %d') as dataVenit, valoare, tip FROM Venit v " +
//            "WHERE (v.data=:data or :data is null) and (v.valoare=:valoare or :valoare is null)")


    @Query(value = "SELECT * FROM Venit v WHERE (v.data=:data or :data is null)" +
            " and (v.valoare=:valoare or :valoare is null)",
            nativeQuery = true)

//    @Query("select v from Venit v where (v.valoare=:valoare or :valoare is null)" +
//            " and (v.data=:data or :data is null)")
    List<Venit> getByValoareDataVenit(@Param("valoare") Double valoare,
                                      @Param("data") Date data);



}
