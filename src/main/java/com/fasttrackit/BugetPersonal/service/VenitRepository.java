package com.fasttrackit.BugetPersonal.service;

import com.fasttrackit.BugetPersonal.model.Venit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VenitRepository extends JpaRepository<Venit, Long> {
    // List<Venit> findByData(Date data);

}
