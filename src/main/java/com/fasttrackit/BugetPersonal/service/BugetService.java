package com.fasttrackit.BugetPersonal.service;

import com.fasttrackit.BugetPersonal.exception.ResourceNotFoundException;
import com.fasttrackit.BugetPersonal.model.Cheltuiala;
import com.fasttrackit.BugetPersonal.model.Venit;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class BugetService {
    private final VenitRepository venitRepository;
    private final CheltuialaRepository cheltuialaRepository;

    public BugetService(VenitReader venitReader, VenitRepository venitRepository,
                        CheltuialaReader cheltuialaReader, CheltuialaRepository cheltuialaRepository) {
        this.venitRepository = venitRepository;
        this.cheltuialaRepository = cheltuialaRepository;
        venitRepository.saveAll((venitReader.getVenituri()));
        cheltuialaRepository.saveAll(cheltuialaReader.getCheltuieli());
    }

    public List<Venit> getAllVenituri() {
        return venitRepository.findAll();
    }

    public List<Cheltuiala> getAllCheltuieli() {
        return cheltuialaRepository.findAll();
    }

    public Venit add(Venit venit) {
        return venitRepository.save(venit);
    }

    public Cheltuiala add(Cheltuiala cheltuiala){
        return cheltuialaRepository.save(cheltuiala);
    }

    public Venit getById(int id) {
        return venitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venitul lipseste", id));
    }

    public List<Venit> getVenituriFiltered(Double valoare, Date dataVenit) {
        return venitRepository.getByValoareDataVenit(valoare, dataVenit);
    }

    public Venit addCheltuialaToVenit(int id, Cheltuiala cheltuiala) {
        Venit venit = getById(id);
        cheltuiala.setVenit(venit);
        venit.getCheltuieli().add(cheltuiala);
        return venitRepository.save(venit);
    }
}
