package com.fasttrackit.BugetPersonal.service;

import com.fasttrackit.BugetPersonal.model.Cheltuiala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Repository
public interface CheltuialaRepository extends JpaRepository<Cheltuiala, Id> {
   // List<Cheltuiala> findByData(Date data);
}
