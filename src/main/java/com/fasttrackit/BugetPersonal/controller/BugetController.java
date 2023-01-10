package com.fasttrackit.BugetPersonal.controller;

import com.fasttrackit.BugetPersonal.model.*;
import com.fasttrackit.BugetPersonal.service.BugetService;
import com.fasttrackit.BugetPersonal.controller.dto.PatchVenitRequest;

import lombok.RequiredArgsConstructor;

import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;



@RequiredArgsConstructor
@RestController
@RequestMapping(value = "buget")  //http://localhost:8080/buget

public class BugetController {
    private final BugetService bugetService;

    @GetMapping(value = "/venituri")  //http://localhost:8080/buget/venituri
    public List<Venit> getAllVenituri(@RequestParam(required = false) Double valoare,
                                      @RequestParam(required = false) TipVenit tip,
                                      @RequestParam(required = false)  Date data) {
        return bugetService.getVenituriFiltered(valoare, tip, data).stream().toList();
        //return bugetService.getAllVenituri().stream().toList();

    }

    @GetMapping(value = "/cheltuieli")  //http://localhost:8080/buget/cheltuieli?valoare=200&tip=TRANSPORT&data=2022-09-01
    public List<Cheltuiala> getAllCheltuieli(@RequestParam(required = false) Double valoare,
                                             @RequestParam(required = false) TipCheltuiala tip,
                                             @RequestParam(required = false) Date data) {
        return bugetService.getCheltuieliFiltered(valoare, tip, data).stream().toList();

    }

    @GetMapping("/venituri/{id}") //  http://host:port/buget/venituri/3
    public Venit getById(@PathVariable Integer id) {
        Venit venit = bugetService.getByIdVenit(id);
        venit.getCheltuieli().stream().count();
        return venit;
    }

    @GetMapping("/cheltuieli/{id}") // http://host:port/buget/cheltuieli/3
    public Cheltuiala getByIdCheltuiala(@PathVariable Integer id) {
        Cheltuiala cheltuiala = bugetService.getByIdCheltuiala(id);
        cheltuiala.getId();
        return cheltuiala;
    }

    @DeleteMapping("/venituri/{id}")
    public Venit deleteVenitById(@PathVariable int id) {
        return bugetService.deleteVenitById(id);
    }

    @DeleteMapping("/cheltuieli/{id}")
    public Cheltuiala deleteCheltuialaById(@PathVariable int id) {
        return bugetService.deleteCheltuialaById(id);
    }

    @PostMapping(value = "/venituri")
    public Venit add(@RequestBody Venit venit) {
        return bugetService.add(venit);
    }

    @PostMapping(value = "/cheltuieli")
    public Cheltuiala add(@RequestBody Cheltuiala cheltuiala) {
        return bugetService.add(cheltuiala);
    }

    @PostMapping("/venituri/{id}/cheltuieli")
    Venit addCheltuialaToVenit(@PathVariable int id, @RequestBody Cheltuiala cheltuiala) {
        return bugetService.addCheltuialaToVenit(id, cheltuiala);
    }

    @PutMapping("/venituri/{id}")
    Venit updateVenit(@PathVariable int id, @RequestBody Venit venit){
        return bugetService.updateVenit(id, venit);
    }

    @PutMapping("/cheltuieli/{id}")
    Cheltuiala updateCheltuiala(@PathVariable int id, @RequestBody Cheltuiala cheltuiala){
        return bugetService.updateCheltuiala(id, cheltuiala);
    }

    @PatchMapping("/venituri/{id}")
    public Venit patch(@PathVariable int id, @RequestBody PatchVenitRequest request) {
        return bugetService.patch(id, request.data(), request.diffValoare());
    }


    @GetMapping(value ="/reports/venituri")  //nu imi trebuie
    public Map<TipVenit, List<Venit>> reportByTipVenit(@RequestParam TipVenit tip)
    {
        return bugetService.getVenituriByTip(tip);
    }

    @GetMapping(value ="/reports/venituri/{data}")  // nu imi trebuie
    public Map<Date, List<Venit>> reportByDataVenit(@PathVariable Date data)
    {
        return bugetService.getVenituriByData(data);
    }


    @GetMapping(value ="/reports/group/venituri")  //functioneaza
    public Map<Date, List<Venit>> reportByAnLunaVenit(@RequestParam String anLuna)
    {
        return bugetService.getVenituriByAnLuna(anLuna);
    }

    @GetMapping(value ="/reports/group/cheltuieli")  // functioneaza doar cu param anLuna
    public Map<CheltuieliAnLunaTip, List<Cheltuiala>> reportByAnLunaTipCheltuiala(@RequestParam(required = false) String anLuna,
                                                                               @RequestParam(required = false) TipCheltuiala tip)
    {
        return bugetService.getCheltuieliByAnLunaTip(anLuna, tip);
    }

    @GetMapping(value ="/reports/cheltuieli/{data}")   //nu imi trebuie
    public Map<Date, List<Cheltuiala>> reportByDataCheltuiala(@PathVariable Date data)
    {
        return bugetService.getCheltuieliByData(data);
    }

    @GetMapping(value ="/reports/cheltuieli")   // nu imi trebuie, nu functioneaza!!!!!!!!!!
    public Map<TipCheltuiala, List<Cheltuiala>> reportByTipCheltuiala(@RequestParam TipCheltuiala tip)
    {
        return bugetService.getCheltuieliByTip(tip);
    }
}
