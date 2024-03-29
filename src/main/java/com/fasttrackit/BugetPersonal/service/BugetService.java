package com.fasttrackit.BugetPersonal.service;

import com.fasttrackit.BugetPersonal.exception.ResourceNotFoundException;
import com.fasttrackit.BugetPersonal.model.*;

import org.springframework.data.annotation.Immutable;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.ImmutablePair;

import static java.util.stream.Collectors.groupingBy;


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

    public Cheltuiala add(Cheltuiala cheltuiala) {
        return cheltuialaRepository.save(cheltuiala);
    }

    public Venit getByIdVenit(int id) {
        return venitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venitul lipseste", id));
    }

    public Cheltuiala getByIdCheltuiala(int id) {
        return cheltuialaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cheltuiala lipseste", id));
    }

    public List<Venit> getVenituriFiltered(Double valoare, TipVenit tip, Date data) {
        return venitRepository.getByValoareTipDataVenit(valoare, tip, data);
    }

    public List<Cheltuiala> getCheltuieliFiltered(Double valoare, TipCheltuiala tip, Date data) {
        return cheltuialaRepository.getByValoareTipDataCheltuiala(valoare, tip, data);
    }

    public Venit addCheltuialaToVenit(int id, Cheltuiala cheltuiala) {
        Venit venit = getByIdVenit(id);
        cheltuiala.setVenit(venit);
        venit.getCheltuieli().add(cheltuiala);
        return venitRepository.save(venit);
    }

    public Venit deleteVenitById(int id) {
        Venit venitToBeDeleted = getByIdVenit(id);
        venitRepository.deleteById(id);
        return venitToBeDeleted;
    }


    public Cheltuiala deleteCheltuialaById(int id) {
        Cheltuiala cheltuialaToBeDeleted = getByIdCheltuiala(id);
        cheltuialaRepository.deleteById(id);
        return cheltuialaToBeDeleted;
    }

    public Venit updateVenit(int id, Venit venit) {
        Venit venitToBeUpdated = getByIdVenit(id);
        venitToBeUpdated.setData(venit.getData());
        venitToBeUpdated.setTip(venit.getTip());
        venitToBeUpdated.setValoare(venit.getValoare());
        return venitRepository.save(venitToBeUpdated);
    }

    public Cheltuiala updateCheltuiala(int id, Cheltuiala cheltuiala) {
        Cheltuiala cheltuialaToBeUpdated = getByIdCheltuiala(id);
        cheltuialaToBeUpdated.setData(cheltuiala.getData());
        cheltuialaToBeUpdated.setTip(cheltuiala.getTip());
        cheltuialaToBeUpdated.setValoare(cheltuiala.getValoare());
        return cheltuialaRepository.save(cheltuialaToBeUpdated);
    }

    public Venit patch(int id, Date data, double diffValoare) {
        Venit venitToBeUpdated = getByIdVenit(id);
        venitToBeUpdated.setData(data);
        venitToBeUpdated.setValoare(venitToBeUpdated.getValoare() + diffValoare);
        return venitRepository.save(venitToBeUpdated);
    }

    public Map<Date, List<Venit>> getVenituriByData(Date data) {
        return venitRepository.getVenituriByData(data)
                .stream()
                .collect(groupingBy(Venit::getData));
    }

    public Map<TipVenit, List<Venit>> getVenituriByTip(TipVenit tip) {
        return venitRepository.getVenituriByTip(tip)
                .stream()
                .collect(groupingBy(Venit::getTip));
    }

    public Map<Date, List<Cheltuiala>> getCheltuieliByData(Date data) {
        return cheltuialaRepository.getCheltuieliByData(data)
                .stream()
                .collect(groupingBy(Cheltuiala::getData));
    }

    public Map<TipCheltuiala, List<Cheltuiala>> getCheltuieliByTip(TipCheltuiala tip) {
        return cheltuialaRepository.getCheltuieliTip(tip)
                .stream()
                .collect(groupingBy(Cheltuiala::getTip));
    }

    public Map<Date, List<Venit>> getVenituriByAnLuna(String anLuna) {
        return venitRepository.getVenituriByAnLuna(anLuna)
                .stream()
                .collect(groupingBy(Venit::getData));
    }


    public Map<CheltuieliAnLunaTip, List<Cheltuiala>> getCheltuieliByAnLunaTip(String anLuna, TipCheltuiala tip) {
        return cheltuialaRepository.getCheltuieliByAnLunaTip(anLuna, tip)
                .stream()
                .collect(groupingBy(cheltuiala -> new CheltuieliAnLunaTip(cheltuiala.getData(), cheltuiala.getTip())));

//        ImmutablePair<String, TipCheltuiala> pair = new ImmutablePair<>(anLuna, tip);
//        String key = pair.getKey();
//        TipCheltuiala value = pair.getValue();
//        return cheltuialaRepository.getCheltuieliByAnLunaTip(anLuna, tip)
//                .stream()
//                .collect(groupingBy(che -> pair<>()));
//
  }
}

