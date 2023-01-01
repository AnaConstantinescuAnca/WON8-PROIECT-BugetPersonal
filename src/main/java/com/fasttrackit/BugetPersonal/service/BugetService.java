package com.fasttrackit.BugetPersonal.service;

import com.fasttrackit.BugetPersonal.model.Cheltuiala;
import com.fasttrackit.BugetPersonal.model.Venit;
import org.springframework.stereotype.Service;

import java.util.List;


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

}
