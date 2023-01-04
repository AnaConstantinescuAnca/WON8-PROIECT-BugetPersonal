package com.fasttrackit.BugetPersonal.service;

import com.fasttrackit.BugetPersonal.model.Venit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VenitRepository extends JpaRepository<Venit, Integer> {
    @Query("select v from Venit v where (v.valoare=:valoare or :valoare is null)" +
    " and (v.data=:dataVenit or :dataVenit is null)")
    List<Venit> getByValoareDataVenit(@Param("valoare") Double valoare,
                                      @Param("dataVenit") Date dataVenit);


}
