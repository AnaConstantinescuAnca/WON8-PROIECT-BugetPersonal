package com.fasttrackit.BugetPersonal.service;

import com.fasttrackit.BugetPersonal.model.Venit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugetRepository extends JpaRepository<Venit, Long> {

}
