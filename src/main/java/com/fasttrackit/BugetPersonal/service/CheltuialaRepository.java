package com.fasttrackit.BugetPersonal.service;

import com.fasttrackit.BugetPersonal.model.Cheltuiala;
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
            " and (c.valoare=:valoare or :valoare is null)")
    List<Cheltuiala> getByValoareDataCheltuiala(@Param("valoare") Double valoare,
                                                @Param("data") Date data);

}
